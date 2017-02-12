package ch08.ex07;

import java.util.Arrays;
import java.util.Comparator;

/**
 * reversed を使わずに nullsFirst(naturalOrder()).reversed()
 *
 * @author 山田晃一
 */
public class AnotherReversed {
	/**
	 * エントリポイント
	 *
	 * @param args
	 *            引数（未使用）
	 */
	public static void main(final String[] args) {
		final String[] strArray = new String[] { "abc", null, "def", null, "ghi" };
		final Comparator<String> comp = Comparator.nullsLast(Comparator.reverseOrder());
		Arrays.sort(strArray, comp);
		System.out.println(Arrays.toString(strArray));
	}
}
