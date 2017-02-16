package ch08.ex15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

/**
 * grep のようなもの
 *
 * @author 山田 晃一
 */
public class Grep {
	/**
	 * エントリポイント
	 *
	 * @param args
	 *            引数
	 *            <li>正規表現</li>
	 *            <li>ファイルパス</li>
	 * @throws IOException
	 *             ファイルが読み込めない。
	 */
	public static void main(final String[] args) throws IOException {
		if (args.length < 2) {
			System.out.println("java Grep REGEX FILE_PATH");
			return;
		}

		// grep("import", "src/ch08/ex15/Grep.java");
		grep(args[0], args[1]);
	}

	public static void grep(final String regex, final String filePath) throws IOException {
		final Pattern pattern = Pattern.compile(regex);
		final Path path = Paths.get(filePath);
		Files.lines(path)
				.filter(pattern.asPredicate())
				.forEach(System.out::println);
	}
}
