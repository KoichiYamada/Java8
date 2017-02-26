package ch09.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * ScannerとPrinterを生成しているコードを手動できちんとクローズする。
 *
 * @author 山田晃一
 */
public class LegacyResourceClose {
	/**
	 * エントリポイント
	 *
	 * @param args
	 *            引数（未使用）
	 * @throws IOException
	 *             元のサンプルがキャッチしていないので、ここでもスルーする。
	 */
	public static void main(final String[] args) throws IOException {
		Scanner in = null;
		PrintWriter out = null;
		try {
			in = new Scanner(Paths.get("res/sdk/src/java/lang/String.java"));
			out = new PrintWriter("src/ch09/ex01/out.txt");
			while (in.hasNext()) {
				out.println(in.next().toLowerCase());
			}
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (final Exception e) {
					// out の close を確実に行うための全キャッチ
				}
			}
			if (out != null) {
				out.close();
			}
		}
	}
}
