package ch08.ex09;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Scanner から 単語、行、整数、double 値 のストリームを作る。
 *
 * @author 山田晃一
 */
public class StreamFromScanner {
	/**
	 * エントリポイント
	 *
	 * @param args
	 *            引数（未使用）
	 * @throws IOException
	 *             ファイルの読み取りエラー
	 */
	public static void main(final String[] args) throws IOException {
		final String path = "src/ch08/ex09/alice.txt";
		final String path1 = "src/ch08/ex09/intDatas.txt";
		final String path2 = "src/ch08/ex09/doubleDatas.txt";

		// word
		System.out.println("word stream -------------------------");
		Scanner scanner = new Scanner(new File(path));
		final Stream<String> words = wordStream(scanner);
		words.limit(10).forEach(System.out::println);
		System.out.println();

		// line
		System.out.println("line stream -------------------------");
		scanner = new Scanner(new File(path));
		final Stream<String> lines = lineStream(scanner);
		lines.limit(10).forEach(System.out::println);
		System.out.println();

		// int
		System.out.println("int stream --------------------------");
		scanner = new Scanner(new File(path1));
		final IntStream ints = intStream(scanner);
		ints.limit(10).forEach(System.out::println);
		System.out.println();

		// double
		System.out.println("double stream -----------------------");
		scanner = new Scanner(new File(path2));
		final DoubleStream doubles = doubleStream(scanner);
		doubles.limit(10).forEach(System.out::println);
		System.out.println();
	}

	/**
	 * 単語の Stream に変換する。
	 *
	 * @param scanner
	 *            変換元の Stream
	 * @return 単語の Stream
	 */
	public static Stream<String> wordStream(final Scanner scanner) {
		return toStream(() -> scanner.hasNext(), () -> scanner.next());
	}

	/**
	 * 行の Stream に変換する。
	 *
	 * @param scanner
	 *            変換元の Stream
	 * @return 行の Stream
	 */
	public static Stream<String> lineStream(final Scanner scanner) {
		return toStream(() -> scanner.hasNextLine(), () -> scanner.nextLine());
	}

	/**
	 * 整数の Stream に変換する。
	 *
	 * @param scanner
	 *            変換元の Stream
	 * @return 整数の Stream
	 */
	public static IntStream intStream(final Scanner scanner) {
		return toStream(() -> scanner.hasNextInt(), () -> scanner.nextInt())
				.mapToInt(Integer::intValue);
	}

	/**
	 * double 値の Stream に変換する。
	 *
	 * @param scanner
	 *            変換元の Stream
	 * @return double 値の Stream
	 */
	public static DoubleStream doubleStream(final Scanner scanner) {
		return toStream(() -> scanner.hasNextDouble(), () -> scanner.nextDouble())
				.mapToDouble(Double::doubleValue);
	}

	private interface HasNext {
		boolean hasNext();
	}

	private interface Next<T> {
		T next();
	}

	/**
	 * 指定された二つの関数で Iterator を作成し、それをソースに Stream を作成する。
	 *
	 * @param hasNext
	 *            Iterator の hasNext 仕様を満たす hasNext
	 * @param next
	 *            Iterator の next 仕様を満たす next
	 * @return Stream
	 */
	private static <T> Stream<T> toStream(final HasNext hasNext, final Next<T> next) {
		final Iterator<T> iter = new Iterator<T>() {
			@Override
			public boolean hasNext() {
				return hasNext.hasNext();
			}

			@Override
			public T next() {
				return next.next();
			}
		};
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
				iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
	}
}
