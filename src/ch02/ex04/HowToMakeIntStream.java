package ch02.ex04;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * intのStreamの作り方
 *
 * @author 山田晃一
 */
public class HowToMakeIntStream {
	/**
	 * エントリポイント
	 *
	 * @param args
	 *            引数
	 */
	public static void main(final String[] args) {
		final int[] values = { 1, 4, 9, 16 };
		// Stream.ofはint[]のStreamになる
		final Stream<int[]> stream = Stream.of(values);
		stream.forEach(System.out::println); // 配列型要素が一つだけ出力される
		// intのStreamは次のように作る
		final IntStream intStream1 = IntStream.of(values);
		intStream1.forEach(System.out::println); // intの配列要素が出力される
		final IntStream intStream2 = Arrays.stream(values);
		intStream2.forEach(System.out::println); // intの配列要素が出力される
		final Stream<Integer> objStream = IntStream.of(values).boxed();
		objStream.forEach(System.out::println); // intの配列要素が出力される
	}
}
