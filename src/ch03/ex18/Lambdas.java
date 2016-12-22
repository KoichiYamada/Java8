package ch03.ex18;

import java.util.function.Function;

/**
 * ラムダユーティリティ
 *
 * @author 山田晃一
 */
public class Lambdas {
    /**
     * 例外を投げる関数をFunction<T, U>に渡せるようにする
     *
     * @param f
     *            処理本体
     * @return U fの結果
     */
    public static <T, U> Function<T, U> unchecked(final ThrowableFunction<T, U> f) {
        return (t) -> {
            try {
                return f.apply(t);
            } catch (final Exception e) {
                throw new RuntimeException("apply throws exception.", e);
            }
        };
    }
}
