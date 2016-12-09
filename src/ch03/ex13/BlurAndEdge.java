package ch03.ex13;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BlurAndEdge extends Application {

	/**
	 * エントリポイント
	 *
	 * @param args
	 *            引数
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		final Image origImage = new Image(new File("src/ch03/ex13/queen-mary.png").toURI().toString());

		// ブラー
		final ColorTransformer blur = (x, y, l) -> {
			double r = 0, g = 0, b = 0;
			int num = 0;

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					final int xx = x + i - 1;
					final int yy = y + j - 1;
					if (xx < 0 || xx >= l.getWidth() || yy < 0 || yy >= l.getHeight()) {
						continue;
					}
					final Color c = l.getColor(xx, yy);
					r += c.getRed();
					g += c.getGreen();
					b += c.getBlue();
					num++;
				}
			}

			final double rr = r / num;
			final double gg = g / num;
			final double bb = b / num;

			return Color.color(rr, gg, bb);
		};

		// エッジ
		final ColorTransformer edge = (x, y, l) -> {
			double r = 0, g = 0, b = 0;
			int num = 0;
			// (x,y-1), (x-1,y), (x+1,y), (x,y+1)
			final int xArray[] = { x, x - 1, x + 1, x };
			final int yArray[] = { y - 1, y, y, y + 1 };

			for (int i = 0; i < 3; i++) {
				final int xx = xArray[i];
				final int yy = yArray[i];
				if (xx < 0 || xx >= l.getWidth() || yy < 0 || yy >= l.getHeight()) {
					continue;
				}
				final Color c = l.getColor(xx, yy);
				r += c.getRed();
				g += c.getGreen();
				b += c.getBlue();
				num++;
			}

			final Color c = l.getColor(x, y);
			final double rr = round(num * c.getRed() - r);
			final double gg = round(num * c.getGreen() - g);
			final double bb = round(num * c.getBlue() - b);

			return Color.color(rr, gg, bb);
		};

		// フレーム
		final ColorTransformer frame = (x, y, l) -> x < 10 || x > l.getWidth() - 10 || y < 10 || y > l.getHeight() - 10
				? Color.GRAY : l.getColor(x, y);

		// /**
		// * <code> ブラー単体とエッジ単体
		final Image blurImage = LatentImage.from(origImage).transform(blur).toImage();
		final Image edgeImage = LatentImage.from(origImage).transform(edge).toImage();
		final HBox origBlueEdge = new HBox(new ImageView(origImage), new ImageView(blurImage),
				new ImageView(edgeImage));
		primaryStage.setScene(new Scene(origBlueEdge));
		// </code>
		// */

		/**
		 * <code> ブラー→フレームとフレーム→ブラー
		final Image blurFrame = LatentImage.from(origImage).transform(blur).transform(frame).toImage();
		final Image frameBlur = LatentImage.from(origImage).transform(frame).transform(blur).toImage();
		final HBox blurAndFrame = new HBox(new ImageView(origImage), new ImageView(blurFrame),
				new ImageView(frameBlur));
		primaryStage.setScene(new Scene(blurAndFrame));
		</code>
		 */

		/**
		 * <code> エッジ→フレームとフレーム→エッジ
		final Image edgeFrame = LatentImage.from(origImage).transform(edge).transform(frame).toImage();
		final Image FrameEdge = LatentImage.from(origImage).transform(frame).transform(edge).toImage();
		final HBox edgeAndFrame = new HBox(new ImageView(origImage), new ImageView(edgeFrame),
				new ImageView(FrameEdge));
		primaryStage.setScene(new Scene(edgeAndFrame));
		</code>
		 */

		primaryStage.show();
	}

	/**
	 * Colorのコンストラクタに適切な値に丸める
	 *
	 * @param d
	 *            丸める値
	 * @return 丸めた値
	 */
	private double round(final double d) {
		if (d < 0)
			return 0;
		if (d > 1)
			return 1;
		return d;
	}
}
