package ch09.ex07;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * ウェブページを読み込み、ファイルに書き出すプログラム。
 *
 * @author 山田晃一
 */
public class WebPageDownloader {
	/**
	 * エントリポイント
	 *
	 * @param args
	 *            引数
	 *            <li>入力ファイル</li>
	 *            <li>出力ファイル</li>
	 * @throws IOException
	 */
	public static void main(final String[] args) throws IOException {
		if (args.length < 2) {
			System.out.println("USAGE: WebPageDownloader URI OUT_FILE");
			return;
		}
		final URL url = new URL(args[0]);
		try (InputStream is = url.openStream()) {
			Files.copy(is, Paths.get(args[1]));
		}
	}
}
