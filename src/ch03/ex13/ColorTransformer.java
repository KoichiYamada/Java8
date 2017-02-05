package ch03.ex13;

import java.util.function.UnaryOperator;

import javafx.scene.paint.Color;

/**
 * 指定位置の色を計算する関数型インターフェイス
 *
 * @author 山田晃一
 */
@FunctionalInterface
public interface ColorTransformer {
	/**
	 * UnariOperator<Color>をColorTransformerへ変える。
	 *
	 * @param op
	 *            元のUnaryOperator<Color>
	 * @return x,yを無視して元と同じ計算をするColorTransformer
	 */
	static ColorTransformer toColorTransformer(final UnaryOperator<Color> op) {
		return (x, y, i) -> op.apply(i.getColor(x, y));
	}

	/**
	 * 指定位置の色を返す
	 *
	 * @param x
	 *            x
	 * @param y
	 *            y
	 * @param image
	 *            適用すべき画像を持つLatentImage
	 * @return 新しい色
	 */
	Color apply(int x, int y, LatentImage image);
}
