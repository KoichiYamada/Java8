package ch02.ex12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 短い単語の出現回数をパラレルでカウントする
 *
 * @author 山田晃一
 */
public class ShortWordCounter {
	/**
	 * データファイルのパス
	 */
	private static String DATA_FILE_PATH = "src/ch02/ex12/alice30.txt";

	/**
	 * エントリポイント
	 *
	 * @param argv
	 *            引数（未使用）
	 * @throws IOException
	 *             ファイルが開けない
	 */
	public static void main(final String[] argv) throws IOException {
		final String contents = new String(Files.readAllBytes(Paths.get(DATA_FILE_PATH)));
		final List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
		final AtomicInteger[] shortWords = new AtomicInteger[12];
		for (int i = 0; i < shortWords.length; i++) {
			shortWords[i] = new AtomicInteger(0);
		}
		words.parallelStream().forEach(s -> {
			if (s.length() < 12) {
				shortWords[s.length()].getAndIncrement();
			}
		});
		System.out.println(Arrays.toString(shortWords));
	}
}
