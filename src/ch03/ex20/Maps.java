package ch03.ex20;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * mapユーティリティ
 *
 * @author 山田晃一
 */
public class Maps {
    /**
     * Listのmap
     *
     * @param l
     *            ソースとなるList
     * @param f
     *            処理する関数
     * @return 処理結果のList
     */
    public static <T, U> List<U> map(final List<T> l, final Function<T, U> f) {
        final List<U> u = new ArrayList<>();
        for (final T t : l) {
            u.add(f.apply(t));
        }
        return u;
    }
}
