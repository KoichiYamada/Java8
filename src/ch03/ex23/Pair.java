package ch03.ex23;

import java.util.function.Function;

/**
 * 同じ型のペアを保持するクラス
 *
 * @author 山田晃一
 * @param <T>
 *            保持する値の型
 */
public class Pair<T> {
    /**
     * 一つ目の値
     */
    private final T first;
    /**
     * 二つ目の値
     */
    private final T second;

    /**
     * コンストラクタ
     *
     * @param first
     *            一つ目の値
     * @param second
     *            二つ目の値
     */
    public Pair(final T first, final T second) {
        this.first = first;
        this.second = second;
    }

    /**
     * 一つ目の値を取得する
     *
     * @return 一つ目の値
     */
    public T getFirst() {
        return first;
    }

    /**
     * 二つ目の値を取得する
     *
     * @return 二つ目の値
     */
    public T getSecond() {
        return second;
    }

    /**
     * 保持するT型の値をU型にマップする
     *
     * @param f
     *            マップする関数
     * @return fの結果作られたPair<U>
     */
    public <U> Pair<U> map(final Function<T, U> f) {
        final U first = f.apply(this.first);
        final U second = f.apply(this.second);
        return new Pair<>(first, second);
    }
}
