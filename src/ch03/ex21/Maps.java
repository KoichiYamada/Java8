package ch03.ex21;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

/**
 * mapユーティリティ
 *
 * @author 山田晃一
 */
public class Maps {
    /**
     * Futureのmap
     *
     * @param t
     *            ソースとなるFuture
     * @param u
     *            処理する関数
     * @return 処理結果のFuture
     */
    public static <T, U> Future<U> map(final Future<T> t, final Function<T, U> u) {
        return new Future<U>() {
            @Override
            public boolean cancel(final boolean mayInterruptIfRunning) {
                return t.cancel(mayInterruptIfRunning);
            }

            @Override
            public boolean isCancelled() {
                return t.isCancelled();
            }

            @Override
            public boolean isDone() {
                return t.isDone();
            }

            @Override
            public U get() throws InterruptedException, ExecutionException {
                return u.apply(t.get());
            }

            @Override
            public U get(final long timeout, final TimeUnit unit)
                    throws InterruptedException, ExecutionException, TimeoutException {
                return u.apply(t.get(timeout, unit));
            }
        };
    }

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
