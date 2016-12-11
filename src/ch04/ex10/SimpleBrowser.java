package ch04.ex10;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * URLバーと進む／戻るボタンだけのブラウザ
 *
 * @author 山田晃一
 */
public class SimpleBrowser extends Application {
	@Override
	public void start(final Stage primaryStage) throws Exception {
		// 部品
		final WebView webView = new WebView();
		final WebEngine webEngine = webView.getEngine();
		final Button backButton = new Button("←");
		backButton.setDisable(true);
		final Button forwardButton = new Button("→");
		forwardButton.setDisable(true);
		final TextField urlField = new TextField();
		// 動作設定
		urlField.setOnAction(e -> webEngine.load(urlField.getText()));
		webEngine.locationProperty().addListener((p, o, n) -> urlField.setText(n));
		webEngine.getHistory().currentIndexProperty().addListener((p, o, n) -> {
			backButton.setDisable(n.intValue() <= 0);
			forwardButton.setDisable(n.intValue() >= (webEngine.getHistory().getEntries().size() - 1));
		});
		backButton.setOnAction(e -> webEngine.getHistory().go(-1));
		forwardButton.setOnAction(e -> webEngine.getHistory().go(1));
		// レイアウト
		final HBox toolBar = new HBox(8);
		toolBar.setPadding(new Insets(8));
		toolBar.getChildren().addAll(backButton, forwardButton, urlField);
		HBox.setHgrow(urlField, Priority.ALWAYS);
		final VBox view = new VBox(toolBar, webView);
		// 表示
		primaryStage.setScene(new Scene(view));
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
