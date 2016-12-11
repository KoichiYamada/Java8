package ch01.ex08;

import java.util.ArrayList;
import java.util.List;

/*
 * Ｑ：これは、正当なコードでしょうか。
 * Ａ：正当
 * Ｑ：各ラムダ式は何をキャプチャするか。
 * Ａ：個々の値
 * Ｑ：従来のループを使用するとどうなるか。
 * Ａ：変わらない。
 */
/**
 * キャプチャの確認クラス
 *
 * @author 山田晃一
 */
public class Capture {
	/**
	 * メイン
	 *
	 * @param args
	 *            引数
	 */
	public static void main(final String[] args) {
		final String[] names = { "Peter", "Paul", "Mary" };
		final List<Runnable> runners = new ArrayList<>();
		for (final String name : names) {
			runners.add(() -> System.out.println(name));
		}
		// 何がキャプチャされたか出力してみる
		for (final Runnable runnable : runners) {
			runnable.run();
		}
		// クリアして配列アクセスの場合どうなるか見る
		runners.clear();
		for (int i = 0; i < names.length; i++) {
			final int j = i;
			runners.add(() -> System.out.println(names[j]));
		}
		// 何がキャプチャされたか出力してみる
		for (final Runnable runnable : runners) {
			runnable.run();
		}
	}
}
