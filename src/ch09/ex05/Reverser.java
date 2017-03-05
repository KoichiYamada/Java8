package ch09.ex05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * ファイルのすべての文字を読み込み、逆順に書き出すプログラム。
 *
 * @author 山田晃一
 */
public class Reverser {
	/**
	 * エントリポイント
	 *
	 * @param args
	 *            引数
	 *            <li>入力ファイル</li>
	 *            <li>出力ファイル</li>
	 * @throws IOException
	 *             入出力エラー
	 */
	public static void main(final String[] args) throws IOException {
		if (args.length < 2) {
			System.out.println("USAGE: Reverser IN_FILE OUT_FILE");
			return;
		}
		final byte[] bytes = Files.readAllBytes(Paths.get(args[0]));
		final String string = new String(bytes, StandardCharsets.UTF_8);
		final StringBuffer buffer = new StringBuffer(string);
		final byte[] reverse = buffer.reverse().toString().getBytes(StandardCharsets.UTF_8);
		Files.write(Paths.get(args[1]), reverse);
	}
}
