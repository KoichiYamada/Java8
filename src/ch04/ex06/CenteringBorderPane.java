package ch04.ex06;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * BorderPaneのTopとBottomのノードをセンタリングする
 *
 * @author 山田晃一
 */
public class CenteringBorderPane extends Application {
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
        final BorderPane pane = new BorderPane();
        // Topノードのセンタリング
        final Button top = new Button("Top");
        pane.setTop(top);
        BorderPane.setAlignment(top, Pos.CENTER);
        pane.setLeft(new Button("Left"));
        pane.setCenter(new Button("Center"));
        pane.setRight(new Button("Right"));
        // Bottomノードのセンタリング
        final Button bottom = new Button("Bottom");
        pane.setBottom(bottom);
        BorderPane.setAlignment(bottom, Pos.CENTER);
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }
}
