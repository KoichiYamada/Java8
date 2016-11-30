package ch03.ex12;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * イメージに対する操作を遅延操作リストとして保持し、任意のタイミングで全て適用したイメージを取り出すことができるクラス
 *
 * @author 山田晃一
 */
public class LatentImage {

	/**
	 * 元画像
	 */
	private Image in;

	/**
	 * 遅延操作リスト
	 */
	private List<ColorTransformer> pendingOperations = new ArrayList<>();

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
		ret.in = image;
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
		pendingOperations.add(f);
		return this;
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
				Color c = in.getPixelReader().getColor(x, y);
				for (final ColorTransformer f : pendingOperations) {
					c = f.apply(x, y, c);
				}
				out.getPixelWriter().setColor(x, y, c);
			}
		}
		return out;
	}
}
