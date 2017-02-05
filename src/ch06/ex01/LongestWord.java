package ch06.ex01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 複数スレッドで最も長い文字列を探す。
 * <p>
 * 最も長い文字列をAtmicReferenceで持ち、累積関数を用いること。
 * </p>
 *
 * @author 山田晃一
 */
public class LongestWord {
	/**
	 * データファイルのパス
	 */
	private static String DATA_FILE_PATH = "src/ch06/ex01/alice30.txt";

	/**
	 * エントリポイント
	 *
	 * @param argv
	 *            引数（未使用）
	 * @throws IOException
	 *             ファイルが開けない
	 */
	public static void main(final String[] args) throws IOException {
		final AtomicReference<String> longestWord = new AtomicReference<>("");
		final String contents = new String(Files.readAllBytes(Paths.get(DATA_FILE_PATH)));
		final List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
		words.parallelStream().forEach(
				word -> longestWord.updateAndGet(x -> word.length() > x.length() ? word : x));
		System.out.println(longestWord.get());
	}
}
