package ch02.ex03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * 並行ストリームのベンチマーク<br>
 * 私のPC(Intel Core i7-6700 3.40GHz / RAM 8GB / 64bit)では、約10倍速い
 *
 * @author 山田晃一
 */
public class StreamBenchmark {
	/**
	 * カウントする単語の長さ（これより長いものをカウントする）
	 */
	private static int COUNT_WORD_LENGTH = 12;
	/**
	 * データファイルのパス
	 */
	private static String DATA_FILE_PATH = "src/ch02/ex03/alice30.txt";

	/**
	 * エントリポイント
	 *
	 * @param argv
	 *            引数
	 * @throws IOException
	 *             データファイルの読み込みに失敗
	 */
	public static void main(final String[] argv) throws IOException {
		final String contents = new String(Files.readAllBytes(Paths.get(DATA_FILE_PATH)));
		final List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
		// ストリーム
		long start = System.nanoTime();
		System.out.println(words.stream().filter(w -> w.length() > COUNT_WORD_LENGTH).count());
		System.out.println(System.nanoTime() - start);
		// 並行ストリーム
		start = System.nanoTime();
		System.out.println(
				words.parallelStream().filter(w -> w.length() > COUNT_WORD_LENGTH).count());
		System.out.println(System.nanoTime() - start);
	}
}
