package ch09.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * close の例外を抑制された例外として元々の例外に追加する。
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
			out = new PrintWriter("src/ch09/ex02/out.txt");
			while (in.hasNext()) {
				out.println(in.next().toLowerCase());
			}
		} catch (final Exception e) {
			if (in != null) {
				try {
					in.close();
				} catch (final Exception e1) {
					e.addSuppressed(e1);
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (final Exception e1) {
					e.addSuppressed(e1);
				}
			}
			throw e;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (final Exception e1) {
					if (out != null) {
						try {
							out.close();
						} catch (final Exception e2) {
							e1.addSuppressed(e2);
							throw e1;
						}
					}
				}
			}
			if (out != null) {
				out.close();
			}
		}
	}
}
