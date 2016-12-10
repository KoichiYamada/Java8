package ch04.ex01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * テキスト入力をラベルに表示する
 *
 * @author 山田晃一
 */
public class TextInput extends Application {

	/**
	 * 下位互換エントリポイント
	 *
	 * @param args
	 *            引数
	 */
	public static void main(final String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage primaryStage) throws Exception {
		final Label message = new Label("Hello, FX");
		message.setFont(new Font(100));
		final TextField input = new TextField("Hello, FX");
		input.textProperty().addListener((p, o, n) -> message.setText(n));
		primaryStage.setScene(new Scene(new VBox(message, input)));
		primaryStage.show();
	}
}
