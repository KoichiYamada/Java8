package ch04.ex07;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * CSSを使わずに中身のコントロールにボーダーをつける
 * <p>
 * 回答：Regionで定義されているsetBorderを使えばよい
 * </p>
 *
 * @author 山田晃一
 */
public class NodeBorder extends Application {

	@Override
	public void start(final Stage primaryStage) throws Exception {
		final double rem = new Text("").getLayoutBounds().getHeight();

		final GridPane pane = new GridPane();
		// pane.setGridLinesVisible(true);

		pane.setHgap(0.8 * rem);
		pane.setVgap(0.8 * rem);
		pane.setPadding(new Insets(0.8 * rem));
		final Label usernameLabel = new Label("User name:");
		final Label passwordLabel = new Label("Password:");
		final TextField username = new TextField();
		final PasswordField password = new PasswordField();

		// ラベルとフィールドに境界を描画するコード
		usernameLabel.setBorder(createBorderWithColor("red"));
		passwordLabel.setBorder(createBorderWithColor("red"));
		username.setBorder(createBorderWithColor("red"));
		password.setBorder(createBorderWithColor("red"));

		final Button okButton = new Button("Ok");
		final Button cancelButton = new Button("Cancel");

		// ボタンに境界を描画するコード
		okButton.setBorder(createBorderWithColor("red"));
		cancelButton.setBorder(createBorderWithColor("red"));

		final HBox buttons = new HBox(0.8 * rem);
		buttons.getChildren().addAll(okButton, cancelButton);
		buttons.setAlignment(Pos.CENTER);
		// buttons.setStyle("-fx-border-color: red;");

		// HBoxの境界を描画するコード
		buttons.setBorder(createBorderWithColor("blue"));

		pane.add(usernameLabel, 0, 0);
		pane.add(username, 1, 0);
		pane.add(passwordLabel, 0, 1);
		pane.add(password, 1, 1);
		pane.add(buttons, 0, 2, 2, 1);

		GridPane.setHalignment(usernameLabel, HPos.RIGHT);
		GridPane.setHalignment(passwordLabel, HPos.RIGHT);
		primaryStage.setScene(new Scene(pane));
		primaryStage.show();
	}

	/**
	 * ボーダーを指定した色で作るヘルパーメソッド
	 *
	 * @param nameOfColor
	 *            ボーダーの色名
	 * @return ボーダー
	 */
	private Border createBorderWithColor(final String nameOfColor) {
		return new Border(new BorderStroke(Paint.valueOf(nameOfColor), BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
				BorderWidths.DEFAULT));
	}

	/**
	 * エントリポイント
	 *
	 * @param args
	 *            引数
	 */
	public static void main(final String[] args) {
		launch(args);
	}
}
