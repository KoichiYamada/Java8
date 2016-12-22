package ch02.ex09;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Stream<ArrayList<T>>をArrayList<T>にまとめる関数群を定義したクラス
 *
 * @author 山田晃一
 */
public class PseudoFlatMap {
    /**
     * Optional<T> reduce(BinaryOperator<T> accumulator)を使った実装
     *
     * @param stream
     *            ひとつにまとめるArrayList<T>のストリーム
     * @return ひとつにまとまったArrayList<T>
     */
    public static <T> ArrayList<T> flat1(final Stream<ArrayList<T>> stream) {
        return stream.reduce((t, u) -> {
            final ArrayList<T> ret = t == null ? new ArrayList<>() : new ArrayList<>(t);
            if (u != null) {
                ret.addAll(u);
            }
            return ret;
        }).orElse(new ArrayList<>());
    }

    /**
     * T reduce(T identity, BinaryOperator<T> accumulator)を使った実装
     *
     * @param stream
     *            ひとつにまとめるArrayList<T>のストリーム
     * @return ひとつにまとまったArrayList<T>
     */
    public static <T> ArrayList<T> flat2(final Stream<ArrayList<T>> stream) {
        return stream.reduce(new ArrayList<T>(), (t, u) -> {
            if (u != null) {
                t.addAll(u);
            }
            return t;
        });
    }

    /**
     * <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U>
     * combiner)を使った実装
     *
     * @param stream
     *            ひとつにまとめるArrayList<T>のストリーム
     * @return ひとつにまとまったArrayList<T>
     */
    public static <T> ArrayList<T> flat3(final Stream<ArrayList<T>> stream) {
        return stream.reduce(new ArrayList<T>(), (t, u) -> {
            if (u != null) {
                t.addAll(u);
            }
            return t;
        }, (t, u) -> {
            final ArrayList<T> ret = t == null ? new ArrayList<>() : new ArrayList<>(t);
            ret.addAll(u);
            return ret;
        });
    }
}
