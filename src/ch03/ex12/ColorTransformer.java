package ch03.ex12;

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
	 * 二つのColorTransformerを合成する。
	 *
	 * @param op1
	 *            一つ目のColorTransformer
	 * @param op2
	 *            二つ目のColorTransformer
	 * @return 合成されたColorTransformer
	 */
	static ColorTransformer compose(final ColorTransformer op1, final ColorTransformer op2) {
		return (x, y, c) -> op2.apply(x, y, op1.apply(x, y, c));
	}

	/**
	 * UnariOperator<Color>をColorTransformerへ変える。
	 *
	 * @param op
	 *            元のUnaryOperator<Color>
	 * @return x,yを無視して元と同じ計算をするColorTransformer
	 */
	static ColorTransformer toColorTransformer(final UnaryOperator<Color> op) {
		return (x, y, c) -> op.apply(c);
	}

	/**
	 * 指定位置の色を返す
	 *
	 * @param x
	 *            x
	 * @param y
	 *            y
	 * @param colorAtXY
	 *            (x, y)の現在の色
	 * @return 新しい色
	 */
	Color apply(int x, int y, Color colorAtXY);
}
