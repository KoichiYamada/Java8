package ch04.ex09;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 惑星に見立てた青い円が楕円軌道を周回する。
 * <p>
 * 軌道はステージから計算する
 * </p>
 *
 * @author 山田晃一
 */
public class Planet extends Application {
    @Override
    public void start(final Stage primaryStage) throws Exception {
        // 惑星
        final Circle planet = new Circle();
        planet.setFill(Color.BLUE);
        planet.radiusProperty().bind(Bindings
                .min(primaryStage.widthProperty(), primaryStage.heightProperty()).divide(20));
        planet.centerXProperty().bind(primaryStage.widthProperty().divide(2));
        planet.centerYProperty().bind(primaryStage.heightProperty().divide(2));
        // 起動
        final Ellipse path = new Ellipse();
        path.radiusXProperty().bind(primaryStage.widthProperty().divide(3));
        path.radiusYProperty().bind(primaryStage.heightProperty().divide(3));
        path.centerXProperty().bind(primaryStage.widthProperty().divide(2));
        path.centerYProperty().bind(primaryStage.heightProperty().divide(2));
        // アニメーション
        final PathTransition anime = new PathTransition(Duration.seconds(2), path, planet);
        anime.setCycleCount(Animation.INDEFINITE);
        anime.setInterpolator(Interpolator.LINEAR);
        anime.play();
        // パスの変更は再スタートしないと反映されない
        primaryStage.widthProperty().addListener((p, o, n) -> restartAnime(anime));
        primaryStage.heightProperty().addListener((p, o, n) -> restartAnime(anime));
        // 表示
        primaryStage.setScene(new Scene(new BorderPane(planet)));
        primaryStage.show();
    }

    /**
     * トランジションを再スタートする
     *
     * @param anime
     *            対象
     */
    private void restartAnime(final Transition anime) {
        anime.stop();
        anime.play();
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
