package ch06.ex07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap<String, Long>内の最大の値を持つキーを見つける。
 *
 * @author 山田晃一
 */
public class LergestValue {
	/**
	 * データファイル1のパス
	 */
	private static String DATA_FILE_PATH = "src/ch06/ex07/alice.txt";

	/**
	 * エントリポイント
	 *
	 * @param args
	 *            引数（未使用）
	 * @throws IOException
	 *             データファイルが開けない。
	 */
	public static void main(final String[] args) throws IOException {
		/*
		 * 準備
		 */
		final ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
		final String contents = new String(Files.readAllBytes(Paths.get(DATA_FILE_PATH)));
		final List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
		words.parallelStream().forEach(word -> map.put(word, (long) word.length()));
		/*
		 * 一番値の大きいキーを見つける
		 */
		final String maxValueKey = map
				.reduceEntries(1, (a, b) -> a.getValue() > b.getValue() ? a : b).getKey();
		System.out.println(maxValueKey + " has value " + map.get(maxValueKey));
	}
}
