package ch04.ex08;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXMLでGridPaneにHBoxがあり、その子にButtonが配置してある。
 * <p>
 * さらにFXMLでプロパティを設定していて、注入によって得たインスタンスを使ってボタンのバインディングをしている。
 * </p>
 *
 * @author 山田晃一
 */
public class FxmlDialog extends Application implements Initializable {
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Button okButton;

	@Override
	public void initialize(final URL location, final ResourceBundle resources) {
		okButton.disableProperty()
				.bind(Bindings.createBooleanBinding(
						() -> (username.getText().length() == 0) || (password.getText().length() == 0),
						username.textProperty(), password.textProperty()));
	}

	@Override
	public void start(final Stage primaryStage) throws Exception {
		final Parent root = FXMLLoader.load(new URL(new File("src/ch04/ex08/dialog.fxml").toURI().toString()));
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
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
