package ch04.ex05;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * スライダーを動かすか、ボタンを押して、スライダーの値が最大か最小になるとボタンが押せなくなるアプリ
 *
 * @author 山田晃一
 */
public class Observing extends Application {

	/**
	 * エントリポイント
	 *
	 * @param args
	 *            引数
	 */
	public static void main(final String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage primaryStage) throws Exception {
		final Button smaller = new Button("Smaller");
		final Slider slider = new Slider(0, 100, 100);
		final Label value = new Label();
		final Button larger = new Button("Larger");

		// Smallerボタンのバインドと動作の設定
		smaller.disableProperty().bind(LatientBinding.observe(t -> t.doubleValue() <= 0, slider.valueProperty()));
		smaller.setOnAction(e -> slider.setValue(slider.getValue() - 10));

		// Largerボタンのバインドと動作の設定
		larger.disableProperty().bind(LatientBinding.observe(t -> t.doubleValue() >= 100, slider.valueProperty()));
		larger.setOnAction(e -> slider.setValue(slider.getValue() + 10));

		// ラベルのバインド
		value.textProperty()
				.bind(LatientBinding.observe(t -> Integer.toString((int) t.doubleValue()), slider.valueProperty()));

		// コンテナの設定
		final HBox hbox = new HBox(smaller, slider, value, larger);
		hbox.setAlignment(Pos.CENTER);

		// 表示
		primaryStage.setScene(new Scene(hbox));
		primaryStage.show();
	}
}
