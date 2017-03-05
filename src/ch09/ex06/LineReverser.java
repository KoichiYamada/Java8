package ch09.ex06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/**
 * ファイルのすべての行を読み込み、逆順に書き出すプログラム。
 *
 * @author 山田晃一
 */
public class LineReverser {
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
			System.out.println("USAGE: LineReverser IN_FILE OUT_FILE");
			return;
		}
		final List<String> lines = Files.readAllLines(Paths.get(args[0]));
		Collections.reverse(lines);
		Files.write(Paths.get(args[1]), lines);
	}
}
