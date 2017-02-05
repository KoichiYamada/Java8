package ch06.ex08;

import java.util.Arrays;
import java.util.Random;

/**
 * Arrays.sortとparallelSort、配列がどれくらいであればparallelSortが早くなるか。
 * <p>
 * 私の環境では大体の個数ではparallelSortの方が早い。
 * </p>
 * <p>
 * 1<<14～1<<16あたりの個数ではparallelSortの方が遅い。テスト方法に問題がある？
 * </p>
 *
 * @author 山田晃一
 */
public class ParallelSortBenchmark {
	private static final int NUM_AVERAGE = 10;

	/**
	 * エントリポイント
	 *
	 * @param args
	 *            引数（未使用）
	 */
	public static void main(final String[] args) {
		final Random r = new Random();
		final long sortTime[] = new long[NUM_AVERAGE];
		final long parallelSortTime[] = new long[NUM_AVERAGE];
		for (int n = (1 << 14);; n += 1) {
			final int fn = n;
			final int[] array = new int[n];
			for (int j = 0; j < NUM_AVERAGE; j++) {
				Arrays.parallelSetAll(array, i -> r.nextInt(fn));
				long start = System.nanoTime();
				Arrays.sort(array);
				long end = System.nanoTime();
				sortTime[j] = end - start;
				//
				Arrays.parallelSetAll(array, i -> r.nextInt(fn));
				start = System.nanoTime();
				Arrays.parallelSort(array);
				end = System.nanoTime();
				parallelSortTime[j] = end - start;
			}
			long sort = 0;
			long parallelSort = 0;
			for (int i = 0; i < NUM_AVERAGE; i++) {
				sort += sortTime[i];
				parallelSort += parallelSortTime[i];
			}
			sort /= NUM_AVERAGE;
			parallelSort /= NUM_AVERAGE;
			System.out.println(n + " numbers");
			System.out.println("  sort         time is " + sort);
			System.out.println("  parallelSort time is " + parallelSort);
			System.out.println("");
			if (sort > parallelSort) {
				break;
			}
		}
	}
}
