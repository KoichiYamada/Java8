package ch06.ex11;

import java.net.PasswordAuthentication;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * repeatを実装する。
 *
 * @author 山田晃一
 */
public class Futures {
	/**
	 * キーボード入力用
	 */
	final static Scanner stdin = new Scanner(System.in);

	/**
	 * エントリポイント
	 *
	 * @param args
	 *            引数（未使用）
	 */
	public static void main(final String[] args) {
		repeat(() -> {
			System.out.print("ユーザー名：");
			final String user = stdin.nextLine();
			System.out.print("パスワード：");
			final String pass = stdin.nextLine();
			return new PasswordAuthentication(user, pass.toCharArray());
		}, auth -> {
			try {
				Thread.sleep(1000);
			} catch (final InterruptedException e) {
			}
			return "secret".equals(new String(auth.getPassword()));
		}).thenAccept(auth -> {
			System.out.println("認証成功：" + auth.getUserName());
		});
		ForkJoinPool.commonPool().awaitQuiescence(1, TimeUnit.MINUTES);
	}

	/**
	 * テストを通過するまで関数を繰り返し適用する。
	 *
	 * @param action
	 *            関数
	 * @param until
	 *            テスト
	 * @return 将来取得可能なT
	 */
	public static <T> CompletableFuture<T> repeat(final Supplier<T> action,
			final Predicate<T> until) {
		return CompletableFuture.supplyAsync(action).thenComposeAsync(t -> {
			return until.test(t) ? CompletableFuture.completedFuture(t) : repeat(action, until);
		});
	}
}
