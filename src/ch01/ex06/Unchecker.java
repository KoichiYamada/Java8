package ch01.ex06;

import java.util.concurrent.Callable;

/*
 * Ｑ：なぜ、RunnableExの代わりにCallable<Void>を使用できないのでしょうか。 Ａ：処理本体に不必要なreturn文を書かなければいけなくなるから。
 */
/**
 * Runnableの例外チェックを不要にするユーティリティメソッドを持つクラス
 *
 * @author 山田晃一
 */
public class Unchecker {
	/**
	 * RunnableExを使うバージョン
	 *
	 * @param runner
	 *            処理本体
	 * @return 例外処理がラップされたRunnable
	 */
	public static Runnable uncheck(final RunnableEx runner) {
		return () -> {
			try {
				runner.run();
			} catch (final Exception e) {
				throw new RuntimeException(e);
			}
		};
	}

	/**
	 * Callable<Void>を使うバージョン
	 *
	 * @param runner
	 *            処理本体
	 * @return 例外処理がラップされたRunnable
	 */
	public static Runnable uncheckWithCallable(final Callable<Void> runner) {
		return () -> {
			try {
				runner.call();
			} catch (final Exception e) {
				throw new RuntimeException(e);
			}
		};
	}
}
