package ch03.ex13;

import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * イメージに対する操作を遅延操作として保持し、任意のタイミングで適用したイメージを 取り出すことができるクラスを複数のピクセルを計算に使えるように強化したクラス
 *
 * @author 山田晃一
 */
public class LatentImage {
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
	private Double width;
	/**
	 * 画像の高さキャッシュ
	 */
	private Double height;
	/**
	 * 色のキャッシュ
	 */
	private final Map<Point2D, Color> colorCache = new HashMap<>();

	/**
	 * ファクトリメソッドでの生成専用にするコンストラクタ
	 */
	private LatentImage() {
	}

	/**
	 * ファクトリメソッド
	 *
	 * @param image
	 *            処理対象画像
	 * @return インスタンス
	 */
	public static LatentImage from(final Image image) {
		final LatentImage ret = new LatentImage();
		ret.orig = image;
		return ret;
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
		final LatentImage ret = new LatentImage();
		ret.in = this;
		ret.transformer = f;
		return ret;
	}

	/**
	 * 画像の幅を取得する
	 *
	 * @return 幅
	 */
	public double getWidth() {
		if (width == null) {
			width = in == null ? orig.getWidth() : in.getWidth();
		}
		return width;
	}

	/**
	 * 画像の高さを取得する
	 *
	 * @return 高さ
	 */
	public double getHeight() {
		if (height == null) {
			height = in == null ? orig.getHeight() : in.getHeight();
		}
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
	public Color getColor(final int x, final int y) {
		final Point2D p = new Point2D(x, y);
		final Color c;
		if (colorCache.containsKey(p)) {
			c = colorCache.get(p);
		} else {
			c = in == null ? orig.getPixelReader().getColor(x, y) : transformer.apply(x, y, in);
			colorCache.put(p, c);
		}
		return c;
	}

	/**
	 * 遅延した操作を実行してイメージを取り出す。
	 *
	 * @return 全ての操作を適用したイメージ
	 */
	public Image toImage() {
		final int width = (int) getWidth();
		final int height = (int) getHeight();
		final WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				final Color c = in == null ? orig.getPixelReader().getColor(x, y)
						: transformer.apply(x, y, in);
				out.getPixelWriter().setColor(x, y, c);
			}
		}
		return out;
	}
}
