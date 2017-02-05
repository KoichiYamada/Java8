package ch03.ex08;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * 指定位置の色を計算する関数型インターフェイス
 *
 * @author 山田晃一
 */
@FunctionalInterface
public interface ColorTransformer {
	/**
	 * 画像に指定した線幅と色でフレームをつけるためのColorTransformerファクトリメソッド
	 *
	 * @param image
	 *            フレームをつける画像
	 * @param frameWidth
	 *            フレームの幅
	 * @param color
	 *            フレームの色
	 * @return ColorTransformer
	 */
	static ColorTransformer create(final Image image, final int frameWidth, final Color color) {
		return (x, y, c) -> (x < frameWidth) || (x > (image.getWidth() - frameWidth))
				|| (y < frameWidth) || (y > (image.getHeight() - frameWidth)) ? color : c;
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
