package ch03.ex07;

import java.util.Comparator;

/**
 * StringのComparatorのファクトリクラス
 *
 * @author 山田晃一
 */
public class ComparatorFactory {
    /**
     * 辞書順のStringのComparatorを返す。compareToと同じ。
     *
     * @return 辞書順のStringのComparator
     */
    public static Comparator<String> create() {
        return create(true, false, false);
    }

    /**
     * 逆順のStringのComparatorを返す。compareToの反対。
     *
     * @return 逆順のStringのComparetor
     */
    public static Comparator<String> createReverseOrder() {
        return create(false, false, false);
    }

    /**
     * 大文字小文字無視のStringのComparatorを返す。compareToIgnoreCaseと同じ。
     *
     * @return 大文字小文字無視のStringのComparator
     */
    public static Comparator<String> createIgnoreCase() {
        return create(true, true, false);
    }

    /**
     * スペースを削除した比較を行うStringのComparatorを返す。
     * 文字列に対して<code>replaceAll(" ", "");</code>してcompareToしたのと同じ。
     *
     * @return スペースを削除した比較を行うStringのComparator
     */
    public static Comparator<String> createIgnoreSpace() {
        return create(true, false, true);
    }

    /**
     * 指定された条件で比較するStringのComparatorを返す。
     *
     * @param normalOrder
     *            辞書順であるか
     * @param ignoreCase
     *            大文字小文字を無視するか
     * @param ignoreSpace
     *            スペースを無視するか
     * @return 指定された条件で比較するStringのComparator
     */
    public static Comparator<String> create(final boolean normalOrder, final boolean ignoreCase,
            final boolean ignoreSpace) {
        return (o1, o2) -> {
            final String l = ignoreSpace ? o1.replaceAll(" ", "") : o1;
            final String r = ignoreSpace ? o2.replaceAll(" ", "") : o2;
            if (ignoreCase) {
                return normalOrder ? l.compareToIgnoreCase(r) : r.compareToIgnoreCase(l);
            } else {
                return normalOrder ? l.compareTo(r) : r.compareTo(l);
            }
        };
    }
}
