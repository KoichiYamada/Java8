package ch08.ex10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * transient と volatile を含む Java のファイルをすべて見つける。
 *
 * @author 山田晃一
 */
public class ReservedInFile {
	/**
	 * エントリポイント
	 *
	 * @param args
	 *            引数（未使用）
	 * @throws IOException
	 *             入出力エラー
	 */
	public static void main(final String[] args) throws IOException {
		final String srcPath = "res/sdk/src/";
		try (final Stream<Path> stream = Files.walk(Paths.get(srcPath))) {
			stream
					.filter(p -> p.toString().endsWith(".java"))
					.filter(p -> contains(p, "transient", "volatile"))
					.forEach(System.out::println);
		}
	}

	/**
	 * ファイル内に指定されたキーワードがあるかチェックする。
	 *
	 * @param path
	 *            ファイルパス
	 * @param words
	 *            キーワード
	 * @return 含まれていたらtrue
	 */
	public static boolean contains(final Path path, final String... words) {
		try {
			return Files.lines(path)
					.filter(line -> {
						for (final String word : words) {
							if (line.contains(word)) {
								return true;
							}
						}
						return false;
					})
					.count() > 0;
		} catch (final IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
