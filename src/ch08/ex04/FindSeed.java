package ch08.ex04;

import java.util.Random;
import java.util.stream.LongStream;

/**
 * Random.nextDouble() で 0 が返る seed を探す。
 *
 * @author 山田晃一
 */
public class FindSeed {
	/**
	 * エントリポイント
	 *
	 * @param args
	 *            引数（未使用）
	 */
	public static void main(final String[] args) {
		/**
		 * <pre>
		 * 何も細工しなければ、私の答えは以下。
		 *
		 * シードは -281474430819444
		 * 189301 回目で 0 が発生。
		 *
		 * しかし問題文は 376050 回で 0 になると言っている。
		 * Random の seed は 48bit しか使われないため、Stream で導いた seed の 48bit だけを見る。
		 * その中から最小値を探せば、ご期待に添えるようだ。
		 * </pre>
		 */

		final long minimumSeed = LongStream.iterate(prev(prev(prev(0))), s -> prev(prev(s)))
				.limit(1_000_000)
				// .map(s -> s ^ M) // 素直な実装。
				.map(s -> (s & ((1L << 48) - 1)) ^ M) // 問題文の期待に添えるように細工。
				.min()
				.orElse(0);

		System.out.println("シードは " + minimumSeed);
		final Random gen = new Random(minimumSeed);

		long i = 1;
		double d = gen.nextDouble();
		while (d != 0) {
			i++;
			d = gen.nextDouble();
		}

		System.out.println(i + " 回目で 0 が発生。");
	}

	private static long A = 11;
	private static long V = 246_154_705_703_781L;
	private static long N = 1L << 48;
	private static long M = 25_214_903_917L;

	public static long prev(final long s) {
		return ((s - A) * V) % N;
	}
}
