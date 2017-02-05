package ch08.ex02;

/**
 * negateExact が例外をスローするこになる整数は何か。
 * <p>
 * JavaDocに、 <i>「引数の否定を返します。結果がintをオーバーフローした場合は例外をスローします。」</i> とある。
 * </p>
 * <p>
 * 否定してオーバーフローする値は一つだけ。
 * </p>
 *
 * @author 山田晃一
 */
public class NegateExact {
	/**
	 * エントリポイント
	 *
	 * @param args
	 *            引数（未使用）
	 */
	public static void main(final String[] args) {
		try {
			Math.negateExact(Integer.MIN_VALUE);
		} catch (final ArithmeticException e) {
			e.printStackTrace();
		}
	}
}
