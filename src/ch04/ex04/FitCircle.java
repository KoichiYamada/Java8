package ch04.ex04;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * 短辺に内接する円
 *
 * @author 山田晃一
 */
public class FitCircle extends Application {
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
        final Circle circle = new Circle();
        final Scene scene = new Scene(new Pane(circle));
        circle.centerXProperty().bind(scene.widthProperty().divide(2));
        circle.centerYProperty().bind(scene.heightProperty().divide(2));
        circle.radiusProperty()
                .bind(Bindings.min(scene.widthProperty(), scene.heightProperty()).divide(2));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
