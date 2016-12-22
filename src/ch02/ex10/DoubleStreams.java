package ch02.ex10;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * DoubleStreamを使わずに、reduceを使って平均を求める練習問題。
 * <p>
 * なぜcountで割れないのか → reduceが終端操作だから、countを呼び出せない
 * </p>
 *
 * @author 山田晃一
 */
public class DoubleStreams {
    /**
     * 平均を求める。
     *
     * @param stream
     *            Doubleのストリーム
     * @return 平均値
     */
    public static double average(final Stream<Double> stream) {
        final AtomicInteger count = new AtomicInteger(0);
        final double sum = stream.reduce(0.0, (t, u) -> {
            count.getAndIncrement();
            return t + u;
        });
        return sum / count.get();
    }
}
