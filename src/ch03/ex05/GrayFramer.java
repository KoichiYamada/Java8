package ch03.ex05;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * グレーの縁取りをするクラス
 *
 * @author 山田晃一
 */
public class GrayFramer extends Application {

	/**
	 * イメージを変更する
	 *
	 * @param in
	 *            入力画像
	 * @param f
	 *            適用する関数
	 * @return 変更後のイメージ
	 */
	public static Image transform(final Image in, final ColorTransformer f) {
		final int width = (int) in.getWidth();
		final int height = (int) in.getHeight();
		final WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				out.getPixelWriter().setColor(x, y, f.apply(x, y, in.getPixelReader().getColor(x, y)));
			}
		}
		return out;
	}

	@Override
	public void start(final Stage primaryStage) throws Exception {
		final Image image = new Image(new File("src/ch03/ex05/queen-mary.png").toURI().toString());
		primaryStage.setScene(new Scene(new Pane(new ImageView(transform(image, (x, y,
				c) -> (x < 10) || (x > (image.getWidth() - 10)) || (y < 10) || (y > (image.getHeight() - 10))
						? Color.GRAY : c)))));
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
