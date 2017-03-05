package ch09.ex08;

/**
 * 座標を表すクラス
 *
 * @author 山田晃一
 */
public class Point {
	private final int x;
	private final int y;

	/**
	 * コンストラクタ
	 *
	 * @param x
	 *            x座標
	 * @param y
	 *            y座標
	 */
	public Point(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * この問題の解答だが、テキストにしっかり書いてあるので違うかも。
	 *
	 * @param other
	 * @return
	 */
	public int compareTo(final Point other) {
		final int diff = Integer.compare(x, other.x);
		if (diff != 0) {
			return diff;
		}
		return Integer.compare(y, other.y);
	}

	/**
	 * エントリポイント
	 *
	 * @param args
	 *            引数（未使用）
	 */
	public static void main(final String[] args) {
		System.out.println(2_000_000_000 - -2_000_000_000); // オーバーフローして正しい符号が得られないケース
		final Point pointP = new Point(2_000_000_000, 0);
		final Point pointN = new Point(-2_000_000_000, 0);
		System.out.println(pointP.compareTo(pointN)); // これなら大丈夫
	}
}
