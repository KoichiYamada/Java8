package ch03.ex16;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 非同期処理ユーティリティ
 *
 * @author 山田晃一
 */
public class Asyncs {
    /**
     * firstの処理結果をsecondで処理する。firstの例外はsecondで、secondの例外はhandlerで処理する。
     * <p>
     * secondの第一引数はfirstの処理結果である。第二引数はfirstで例外が起きた場合はその例外が、起きなければnullが渡る。
     * </p>
     * <p>
     * ex16の解答：secondで発生した例外を処理するために第三引数は必要。
     * 第二引数のThrowableに渡すことも出来るが、その場合、firstで起きた例外か、secondで起きた例外かが判別できない。
     * </p>
     *
     * @param first
     *            Tを返す第一処理
     * @param second
     *            Tを入力とする第二処理で、第一処理の例外処理も行う
     * @param handler
     *            第二処理の例外を処理する
     */
    public static <T> void doInOrderAsync(final Supplier<T> first,
            final BiConsumer<T, Throwable> second, final Consumer<Throwable> handler) {
        new Thread(() -> {
            T result;
            Throwable t;
            try {
                result = first.get();
                t = null;
            } catch (final Throwable e) {
                result = null;
                t = e;
            }
            try {
                second.accept(result, t);
            } catch (final Throwable e) {
                handler.accept(e);
            }
        }).start();
    }
}
