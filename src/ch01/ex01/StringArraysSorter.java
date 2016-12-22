package ch01.ex01;

import java.util.Arrays;

/**
 * Stringの配列をソートする。
 *
 * @author 山田晃一
 */
public class StringArraysSorter {
    /**
     * 引数stringsに指定された配列をソートする。
     *
     * @param strings
     *            ソートするString配列
     * @return ソートされたstrings（同じインスタンス）
     */
    public String[] sort(final String[] strings) {
        Arrays.sort(strings, this::compare);
        return strings;
    }

    /**
     * ソートに使用する比較関数
     *
     * @param first
     *            比較の左辺値
     * @param second
     *            比較の右辺値
     * @return first < secondならマイナス、同じなら0、first > secondならプラスの値を返す。
     */
    public int compare(final String first, final String second) {
        return first.compareTo(second);
    }
}
