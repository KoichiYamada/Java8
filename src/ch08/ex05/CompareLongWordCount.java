package ch08.ex05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * ストリームとラムダの速度比較
 * <p>
 * リストが小さいときはラムダの方が遥かに早く、リストが大きいときはストリームの方がやや早い。
 * </p>
 *
 * @author 山田晃一
 */
public class CompareLongWordCount {
	/**
	 * カウントする単語の長さ（これより長いものをカウントする）
	 */
	private static int COUNT_WORD_LENGTH = 12;
	/**
	 * データファイルのパス
	 */
	private static String DATA_FILE_PATH = "src/ch08/ex05/alice.txt";
	private static String DATA_FILE_PATH2 = "src/ch08/ex05/war-and-peace.txt";

	/**
	 * エントリポイント
	 *
	 * @param args
	 *            引数（未使用）
	 * @throws IOException
	 *             ファイルが読めなかったとき
	 */
	public static void main(final String[] args) throws IOException {
		alice();
		warAndPeace();
	}

	public static void alice() throws IOException {
		measure(DATA_FILE_PATH);
	}

	public static void warAndPeace() throws IOException {
		measure(DATA_FILE_PATH2);
	}

	public static void measure(final String filePath) throws IOException {
		final String contents = new String(Files.readAllBytes(Paths.get(filePath)));
		final List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
		System.out.println("list size :" + words.size());
		// ストリーム
		System.out.println("stream");
		long start = System.nanoTime();
		System.out.println(words.stream().filter(w -> w.length() > COUNT_WORD_LENGTH).count());
		System.out.println(System.nanoTime() - start);
		System.out.println();
		// ラムダ
		System.out.println("lambda");
		start = System.nanoTime();
		final long[] count = new long[] { 0 };
		words.forEach(w -> {
			if (w.length() > COUNT_WORD_LENGTH) {
				count[0]++;
			}
		});
		System.out.println(count[0]);
		System.out.println(System.nanoTime() - start);
		System.out.println();
	}
}
