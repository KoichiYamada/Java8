package ch08.ex03;

/**
 * 三つの実装のうち、負の値に対して簡単なものはどれか。
 * <p>
 * remを実装することを考えると、どれも一緒。remが提供されていたら、remでやった方がほんの一手間だけ簡単。
 * </p>
 *
 * @author 山田晃一
 */
public class GCD {
	/**
	 * エントリポイント
	 *
	 * @param args
	 *            引数（未使用）
	 */
	public static void main(final String[] args) {
		System.out.println(gcdOperator(24, -36));
		System.out.println(gcdFloorMod(24, -36));
		System.out.println(gcdRem(24, -36));
	}

	/**
	 * %を使って最大公約数を求める。
	 *
	 * @param a
	 *            数値１
	 * @param b
	 *            数値２
	 * @return 最大公約数
	 */
	public static long gcdOperator(long a, long b) {
		long c;

		while (b != 0) {
			c = a;
			a = b;
			b %= c;
		}

		return Math.abs(a);
	}

	/**
	 * floorModを使って最大公約数を求める。
	 *
	 * @param a
	 *            数値１
	 * @param b
	 *            数値２
	 * @return 最大公約数
	 */
	public static long gcdFloorMod(long a, long b) {
		long c;

		while (b != 0) {
			c = a;
			a = b;
			b = Math.floorMod(c, b);
		}

		return Math.abs(a);
	}

	/**
	 * remを使って最大公約数を求める。
	 *
	 * @param a
	 *            数値１
	 * @param b
	 *            数値２
	 * @return 最大公約数
	 */
	public static long gcdRem(long a, long b) {
		long c;

		while (b != 0) {
			c = a;
			a = b;
			b = rem(c, b);
		}

		return a;
	}

	/**
	 * aをbで割った数学的余りを求める。
	 *
	 * @param a
	 *            数値１
	 * @param b
	 *            数値２
	 * @return 数学的な余り
	 */
	public static long rem(final long a, final long b) {
		return Math.abs(a % b);
	}
}
