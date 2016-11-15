package ch02.ex05;

import java.util.stream.Stream;

/**
 * ランダムストリーム
 *
 * @author 山田晃一
 */
public class RandomStream {

	/**
	 * エントリポイント
	 *
	 * @param args
	 *            引数
	 */
	public static void main(String[] args) {
		final Stream<Long> stream = RandomStream.generate(25214903917L, 11, (long) Math.pow(2, 18), System.nanoTime());
		stream.limit(10).forEach(System.out::println);
	}

	/**
	 * 線形合同法で疑似乱数の無限ストリームを生成する
	 *
	 * @param a
	 * @param c
	 * @param m
	 * @param seed
	 * @return 疑似乱数ストリーム
	 */
	public static Stream<Long> generate(final long a, final long c, final long m, final long seed) {
		return Stream.iterate(seed, x -> (a * x + c) % m).skip(1); // seedは飛ばす
	}
}
