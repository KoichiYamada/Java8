package ch03.ex05;

import javafx.scene.paint.Color;

/**
 * 指定位置の色を計算する関数型インターフェイス
 *
 * @author 山田晃一
 */
@FunctionalInterface
public interface ColorTransformer {
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
