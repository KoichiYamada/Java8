package ch06.ex03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * AtomicLongとLongAddrをベンチマークする。
 *
 * @author 山田晃一
 */
public class Benchmark {
    /**
     * スレッド数
     */
    private static final int NUM_THREAD = 1000;
    /**
     * 加算回数
     */
    private static final int NUM_INCREMENT = 100_000;
    /**
     * タイムアウト
     */
    private static final int TIMEOUT_MINUTE = 3;

    /**
     * エントリポイント
     *
     * @param args
     *            引数（未使用）
     * @throws InterruptedException
     *             スレッド割り込み発生
     */
    public static void main(final String[] args) throws InterruptedException {
        // AtomicLong
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREAD);
        final AtomicLong countAL = new AtomicLong(0);
        long start = System.nanoTime();
        System.out.println("AtmicLong");
        for (int i = 0; i < NUM_THREAD; i++) {
            executor.execute(() -> {
                for (int j = 0; j < NUM_INCREMENT; j++) {
                    countAL.incrementAndGet();
                }
            });
        }
        executor.shutdown();
        executor.awaitTermination(TIMEOUT_MINUTE, TimeUnit.MINUTES);
        System.out.println("合計：" + countAL.get());
        System.out.println("経過時間：" + ((System.nanoTime() - start) / 1e9) + "s");
        // LongAdder
        executor = Executors.newFixedThreadPool(NUM_THREAD);
        final LongAdder countLA = new LongAdder();
        start = System.nanoTime();
        System.out.println("LongAdder");
        for (int i = 0; i < NUM_THREAD; i++) {
            executor.execute(() -> {
                for (int j = 0; j < NUM_INCREMENT; j++) {
                    countLA.increment();
                }
            });
        }
        executor.shutdown();
        executor.awaitTermination(TIMEOUT_MINUTE, TimeUnit.MINUTES);
        System.out.println("合計：" + countLA.sum());
        System.out.println("経過時間：" + ((System.nanoTime() - start) / 1e9) + "s");
    }
}
