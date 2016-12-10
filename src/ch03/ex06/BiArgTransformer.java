package ch03.ex06;

import java.io.File;
import java.util.function.BiFunction;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * 引数二つの関数型インターフェイスの問題
 *
 * @author 山田晃一
 */
public class BiArgTransformer extends Application {

	/**
	 * イメージに引数を二つ取る関数を適用する
	 *
	 * @param in
	 *            入力画像
	 * @param f
	 *            引数二つの関数型インターフェイス
	 * @param arg
	 *            fに与える二つ目の引数（ひとつ目は元の色）
	 * @return fを適用した画像
	 */
	public static <T> Image transform(final Image in, final BiFunction<Color, T, Color> f, final T arg) {
		final int width = (int) in.getWidth();
		final int height = (int) in.getHeight();
		final WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				out.getPixelWriter().setColor(x, y, f.apply(in.getPixelReader().getColor(x, y), arg));
			}
		}
		return out;
	}

	@Override
	public void start(final Stage primaryStage) throws Exception {
		final Image image = new Image(new File("src/ch03/ex06/queen-mary.png").toURI().toString());
		primaryStage.setScene(new Scene(new HBox(new ImageView(image),
				new ImageView(transform(image, (c, arg) -> c.deriveColor(0, 1, arg, 1), 1.2)))));
		primaryStage.show();
	}

	/**
	 * 下位互換用エントリポイント。
	 *
	 * @param args
	 *            引数
	 */
	public static void main(final String[] args) {
		launch(args);
	}
}
