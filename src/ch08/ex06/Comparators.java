package ch08.ex06;

import java.util.Comparator;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

/**
 * 全順序なコンパレータの定義
 *
 * @author 山田晃一
 */
public class Comparators {
	/**
	 * Point2D の全順序なコンパレータ
	 */
	public static final Comparator<Point2D> TOTAL_ORDERING_COMPARATOR_OF_POINT2D = Comparator
			.comparingDouble(Point2D::getX).thenComparingDouble(Point2D::getY);

	/**
	 * Rectangle2D の全順序なコンパレータ
	 */
	public static final Comparator<Rectangle2D> TOTAL_ORDERING_COMPARATOR_OF_RECTANGLE2D = Comparator
			.comparingDouble(Rectangle2D::getMinX).thenComparingDouble(Rectangle2D::getMinY)
			.thenComparingDouble(Rectangle2D::getMaxX).thenComparingDouble(Rectangle2D::getMaxY);

	/**
	 * エントリポイント
	 *
	 * @param args
	 *            引数（未使用）
	 */
	public static void main(final String[] args) {
		final Point2D p1 = new Point2D(10, 20);
		final Point2D p2 = new Point2D(10, 30);
		System.out.println(TOTAL_ORDERING_COMPARATOR_OF_POINT2D.compare(p1, p1));
		System.out.println(TOTAL_ORDERING_COMPARATOR_OF_POINT2D.compare(p1, p2));
		System.out.println(TOTAL_ORDERING_COMPARATOR_OF_POINT2D.compare(p2, p1));

		final Rectangle2D r1 = new Rectangle2D(10, 10, 20, 20);
		final Rectangle2D r2 = new Rectangle2D(10, 10, 30, 30);
		System.out.println(TOTAL_ORDERING_COMPARATOR_OF_RECTANGLE2D.compare(r1, r1));
		System.out.println(TOTAL_ORDERING_COMPARATOR_OF_RECTANGLE2D.compare(r1, r2));
		System.out.println(TOTAL_ORDERING_COMPARATOR_OF_RECTANGLE2D.compare(r2, r1));
	}
}
