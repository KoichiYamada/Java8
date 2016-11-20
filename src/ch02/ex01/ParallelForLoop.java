package ch02.ex01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * 並行ループは以下の通り。
 *
 * Ｑ：カウンターを更新するためにスレッドを使用したくないのはなぜか
 * Ａ：カウンターの排他を取らなければならなくなるから。スレッドの終了を待たなければならなくなるから。
 */

/**
 * 並行ループ
 *
 * @author 山田晃一
 */
public class ParallelForLoop {

	/**
	 * セグメントサイズ
	 */
	private static int SEGMENT_SIZE = 100;

	/**
	 * カウントする単語の長さ（これより長いものをカウントする）
	 */
	private static int COUNT_WORD_LENGTH = 12;

	/**
	 * データファイルのパス
	 */
	private static String DATA_FILE_PATH = "src/ch02/ex01/alice30.txt";

	/**
	 * エントリポイント
	 *
	 * @param argv
	 *            引数
	 * @throws IOException
	 *             データファイルの読み込みに失敗
	 * @throws InterruptedException
	 *             スレッドの待ち合わせに割り込み発生
	 */
	public static void main(final String[] argv) throws IOException, InterruptedException {
		final String contents = new String(Files.readAllBytes(Paths.get(DATA_FILE_PATH)));
		final List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
		// 並行ループ
		System.out.println(ParallelForLoop.countLongWordFrom(words));
		// 並行ストリーム（検算用）
		System.out.println(words.parallelStream().filter(w -> w.length() > COUNT_WORD_LENGTH).count());
	}

	/**
	 * 並行ループで長い単語をカウントする
	 *
	 * @param words
	 *            単語リスト
	 * @return カウント
	 * @throws InterruptedException
	 *             スレッドの待ち合わせに割り込み発生
	 */
	public static int countLongWordFrom(final List<String> words) throws InterruptedException {
		final AtomicInteger count = new AtomicInteger(0);
		final List<Thread> threads = new ArrayList<>();

		for (int i = 0; i < words.size(); i += SEGMENT_SIZE) {
			// セグメントサイズ分取り出し
			final List<String> subWords;
			if (i + SEGMENT_SIZE < words.size()) {
				subWords = words.subList(i, i + SEGMENT_SIZE);
			} else {
				subWords = words.subList(i, words.size());
			}

			// セグメントを処理するスレッドを起動
			final Thread thread = new Thread(() -> {
				int subCount = 0;
				for (final String w : subWords) {
					if (w.length() > COUNT_WORD_LENGTH) {
						subCount++;
					}
				}
				// セグメントごとに集計
				count.getAndAdd(subCount);
			});
			thread.start();
			threads.add(thread);
		}

		// 全セグメントの終了を待つ
		for (final Thread t : threads) {
			t.join();
		}

		return count.get();
	}
}
