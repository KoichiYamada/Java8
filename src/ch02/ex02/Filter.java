package ch02.ex02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Filterがリダクション完了以降呼ばれないことの確認
 *
 * @author 山田晃一
 */
public class Filter {

	/**
	 * カウントする単語の長さ（これより長いものをカウントする）
	 */
	private static int COUNT_WORD_LENGTH = 12;

	/**
	 * データファイルのパス
	 */
	private static String DATA_FILE_PATH = "src/ch02/ex02/alice30.txt";

	/**
	 * エントリポイント
	 *
	 * @param args
	 *            引数
	 * @throws IOException
	 *             データファイルの読み取り失敗
	 */
	public static void main(String[] args) throws IOException {
		final String contents = new String(Files.readAllBytes(Paths.get(DATA_FILE_PATH)));
		final List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

		words.stream().filter(w -> {
			// 呼ばれるたびに出すと出すぎて分からなくなるので、trueの時だけ出力
			if (w.length() > COUNT_WORD_LENGTH) {
				System.out.println("filter");
			}
			return w.length() > COUNT_WORD_LENGTH;
		}).limit(5).count();
	}
}
