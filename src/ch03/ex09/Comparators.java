package ch03.ex09;

import java.lang.reflect.Field;
import java.util.Comparator;

/**
 * Comparatorのコンパニオンクラス
 *
 * @author 山田晃一
 */
public class Comparators {

	/**
	 * 与えられたフィールド名の順序で比較を行うComparetorを返す。
	 *
	 * @param fieldNames
	 *            比較するフィールド名の配列
	 * @return 与えられたフィールド名の順序で比較を行うComparetor
	 */
	public static <T> Comparator<T> lexicographicComparator(final String... fieldNames) {
		return (o1, o2) -> {
			for (final String fieldName : fieldNames) {
				final Field f1, f2;
				try {
					f1 = o1.getClass().getDeclaredField(fieldName);
					f2 = o2.getClass().getDeclaredField(fieldName);
					f1.setAccessible(true);
					f2.setAccessible(true);
					if (f1.get(o1) instanceof Comparable) {
						// instanceof してるので安全
						@SuppressWarnings("unchecked")
						final int ret = ((Comparable<T>) f1.get(o1)).compareTo((T) f2.get(o2));
						if (ret != 0) {
							return ret;
						}
					}
				} catch (NoSuchFieldException | SecurityException | IllegalArgumentException
						| IllegalAccessException e) {
					throw new IllegalArgumentException("can't access to field.", e);
				}
			}
			return 0;
		};
	}
}
