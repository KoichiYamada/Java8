package ch03.ex12;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * グレーの縁取りをするクラス
 *
 * @author 山田晃一
 */
public class GrayFramer extends Application {
	@Override
	public void start(final Stage primaryStage) throws Exception {
		final Image image = new Image(new File("src/ch03/ex12/queen-mary.png").toURI().toString());
		final ColorTransformer f = (x, y, c) -> (x < 10) || (x > (image.getWidth() - 10))
				|| (y < 10) || (y > (image.getHeight() - 10)) ? Color.GRAY : c;
		final Image afterImage = LatentImage.from(image).transform(Color::brighter).transform(f)
				.toImage();
		primaryStage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(afterImage))));
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
