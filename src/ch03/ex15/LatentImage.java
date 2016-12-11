package ch03.ex15;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.paint.Color;

/**
 * イメージに対する操作を遅延操作として保持し、任意のタイミングで適用したイメージを
 * 取り出すことができるクラスを複数のピクセルを計算に使えるように強化したクラス
 *
 * @author 山田晃一
 */
public class LatentImage implements PixelReader {
	/**
	 * 元画像
	 */
	private Image orig;
	/**
	 * 前段階の画像
	 */
	private LatentImage in;
	/**
	 * 今段階の操作
	 */
	private ColorTransformer transformer;
	/**
	 * 画像の幅キャッシュ
	 */
	private double width;
	/**
	 * 画像の高さキャッシュ
	 */
	private double height;
	/**
	 * 色のキャッシュ
	 */
	private Color[][] colorCache;

	/**
	 * ファクトリメソッドでの生成専用にするコンストラクタ
	 */
	private LatentImage() {
	}

	/**
	 * オリジナル画像を設定するコンストラクタ
	 *
	 * @param image
	 *            オリジナル画像
	 */
	private LatentImage(final Image image) {
		orig = image;
		width = image.getWidth();
		height = image.getHeight();
		colorCache = new Color[(int) width][(int) height];
	}

	/**
	 * チェインするコンストラクタ
	 *
	 * @param in
	 *            チェインするLatentImage
	 * @param f
	 *            トランスフォーマ
	 */
	private LatentImage(final LatentImage in, final ColorTransformer f) {
		this.in = in;
		width = in.getWidth();
		height = in.getHeight();
		colorCache = new Color[(int) width][(int) height];
		transformer = f;
	}

	/**
	 * ファクトリメソッド
	 *
	 * @param image
	 *            処理対象画像
	 * @return インスタンス
	 */
	public static LatentImage from(final Image image) {
		return new LatentImage(image);
	}

	/**
	 * UnaryOperator<Color>を遅延操作として追加する
	 *
	 * @param f
	 *            操作
	 * @return インスタンス
	 */
	public LatentImage transform(final UnaryOperator<Color> f) {
		return transform(ColorTransformer.toColorTransformer(f));
	}

	/**
	 * ColorTransformerを遅延操作として追加する
	 *
	 * @param f
	 *            操作
	 * @return インスタンス
	 */
	public LatentImage transform(final ColorTransformer f) {
		return new LatentImage(this, f);
	}

	/**
	 * 画像の幅を取得する
	 *
	 * @return 幅
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * 画像の高さを取得する
	 *
	 * @return 高さ
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * 指定座標の色を取得する
	 *
	 * @param x
	 *            幅方向位置
	 * @param y
	 *            高さ方向位置
	 * @return 色
	 */
	@Override
	public Color getColor(final int x, final int y) {
		final Color c;
		if (colorCache[x][y] == null) {
			c = in == null ? orig.getPixelReader().getColor(x, y) : transformer.apply(x, y, in);
			colorCache[x][y] = c;
		} else {
			c = colorCache[x][y];
		}
		return c;
	}

	/**
	 * 遅延した操作を実行してイメージを取り出す。
	 *
	 * @return 全ての操作を適用したイメージ
	 */
	public Image toImage() {
		final int width = (int) in.getWidth();
		final int height = (int) in.getHeight();
		final WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				final Color c = in == null ? orig.getPixelReader().getColor(x, y) : transformer.apply(x, y, in);
				out.getPixelWriter().setColor(x, y, c);
			}
		}
		return out;
	}

	/**
	 * 遅延した操作を並列実行してイメージを取り出す。
	 *
	 * @return 全ての操作を適用したイメージ
	 * @throws InterruptedException
	 *             並列処理に割り込まれた場合
	 */
	public Image toImageParallel() throws InterruptedException {
		final int width = (int) in.getWidth();
		final int height = (int) in.getHeight();
		final Color[][] colors = new Color[width][height];
		final int n = Runtime.getRuntime().availableProcessors();
		final int b = (int) Math.ceil(height / n);
		final ExecutorService pool = Executors.newCachedThreadPool();
		for (int i = 0; i < n; i++) {
			final int fromY = b * i;
			final int toY = (fromY + b) > height ? height : fromY + b;
			pool.submit(() -> {
				for (int x = 0; x < width; x++) {
					for (int y = fromY; y < toY; y++) {
						final Color c = in == null ? orig.getPixelReader().getColor(x, y) : transformer.apply(x, y, in);
						colors[x][y] = c;
					}
				}
			});
		}
		pool.shutdown();
		pool.awaitTermination(10, TimeUnit.MINUTES);
		final WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				out.getPixelWriter().setColor(x, y, colors[x][y]);
			}
		}
		return out;
	}

	@Override
	public PixelFormat getPixelFormat() {
		/**
		 * 単に委譲しようかと思ったが、transformerを適用すべきなのか？未サポートにしておく。 <code>
		return in == null ? orig.getPixelReader().getPixelFormat() : in.getPixelFormat();
		 * </code>
		 */
		throw new UnsupportedOperationException("not support");
	}

	@Override
	public int getArgb(final int x, final int y) {
		/**
		 * 単に委譲しようかと思ったが、transformerを適用すべきなのか？未サポートにしておく。 <code>
		return in == null ? orig.getPixelReader().getArgb(x, y) : in.getArgb(x, y);
		 * </code>
		 */
		throw new UnsupportedOperationException("not support");
	}

	@Override
	public <T extends Buffer> void getPixels(final int x, final int y, final int w, final int h,
			final WritablePixelFormat<T> pixelformat, final T buffer,
			final int scanlineStride) {
		/**
		 * 単に委譲しようかと思ったが、transformerを適用すべきなのか？未サポートにしておく。 <code>
		if (in == null) {
			orig.getPixelReader().getPixels(x, y, w, h, pixelformat, buffer, scanlineStride);
		} else {
			in.getPixels(x, y, w, h, pixelformat, buffer, scanlineStride);
		}
		 * </code>
		 */
		throw new UnsupportedOperationException("not support");
	}

	@Override
	public void getPixels(final int x, final int y, final int w, final int h,
			final WritablePixelFormat<ByteBuffer> pixelformat, final byte[] buffer,
			final int offset, final int scanlineStride) {
		/**
		 * 単に委譲しようかと思ったが、transformerを適用すべきなのか？未サポートにしておく。 <code>
		if (in == null) {
			orig.getPixelReader().getPixels(x, y, w, h, pixelformat, buffer, offset, scanlineStride);
		} else {
			in.getPixels(x, y, w, h, pixelformat, buffer, offset, scanlineStride);
		}
		 * </code>
		 */
		throw new UnsupportedOperationException("not support");
	}

	@Override
	public void getPixels(final int x, final int y, final int w, final int h,
			final WritablePixelFormat<IntBuffer> pixelformat, final int[] buffer,
			final int offset, final int scanlineStride) {
		/**
		 * 単に委譲しようかと思ったが、transformerを適用すべきなのか？未サポートにしておく。 <code>
		if (in == null) {
			orig.getPixelReader().getPixels(x, y, w, h, pixelformat, buffer, offset, scanlineStride);
		} else {
			in.getPixels(x, y, w, h, pixelformat, buffer, offset, scanlineStride);
		}
		 * </code>
		 */
		throw new UnsupportedOperationException("not support");
	}
}
