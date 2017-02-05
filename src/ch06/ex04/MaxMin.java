package ch06.ex04;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.Stream;

/**
 * LongAccumulatorを使用して、要素の最大値、あるいは最小値を計算する。
 *
 * @author 山田晃一
 */
public class MaxMin {
	/**
	 * 乱数数
	 */
	private static final int COUNT = 1_000_000;

	/**
	 * エントリポイント
	 *
	 * @param args
	 *            引数（未使用）
	 */
	public static void main(final String[] args) {
		// 最大値を求めてみる
		Stream<Long> source = Stream.generate(() -> (long) (Math.random() * COUNT));
		final LongAccumulator maxLongAccumulator = new LongAccumulator(Math::max, Long.MIN_VALUE);
		source.parallel().limit(COUNT).forEach(maxLongAccumulator::accumulate);
		System.out.println(maxLongAccumulator.get());
		// 最小値を求めてみる
		source = Stream.generate(() -> (long) (Math.random() * COUNT));
		final LongAccumulator minLongAccumulator = new LongAccumulator(Math::min, Long.MAX_VALUE);
		source.parallel().limit(COUNT).forEach(minLongAccumulator::accumulate);
		System.out.println(minLongAccumulator.get());
	}
}
