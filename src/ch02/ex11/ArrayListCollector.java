package ch02.ex11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;
import java.util.stream.Stream;

/**
 * サプライヤーで同一のインスタンスを返すようにする。<br>
 * アキュムレーターでは一意の位置にaddするようにする。<br>
 * コンバイナーは何もしない。<br>
 * で、いけそうな気がする。ただし、streamのサイズが与えられれば。
 *
 * @author 山田晃一
 */
public class ArrayListCollector {

	/**
	 * データファイルのパス
	 */
	private static String DATA_FILE_PATH = "src\\ch02\\ex11\\alice30.txt";

	/**
	 * エントリポイント
	 *
	 * @param argv
	 *            引数（未使用）
	 * @throws IOException
	 *             ファイルが読み込めない
	 */
	public static void main(final String[] argv) throws IOException {
		final String contents = new String(Files.readAllBytes(Paths.get(DATA_FILE_PATH)));
		final List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

		final List<String> result = ArrayListCollector.collect(words.stream().parallel(), words.size());
		result.stream().limit(100).forEach(System.out::println);
	}

	/**
	 * コレクト
	 *
	 * @param stream
	 *            収集対象のストリーム
	 * @param num
	 *            ストリームの個数
	 * @return 収集したArrayList
	 */
	public static <T> ArrayList<T> collect(final Stream<T> stream, final int num) {
		final ArrayList<T> result = new ArrayList<>(num);
		final List<T> list = Collections.synchronizedList(result);

		stream.collect(Collector.of(() -> list, (c, e) -> c.add(e), (a, b) -> list, Characteristics.IDENTITY_FINISH));

		return result;
	}
}
