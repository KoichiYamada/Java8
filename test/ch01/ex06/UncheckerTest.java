package ch01.ex06;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Uncheckerのテストクラス
 *
 * @author 山田晃一
 */
public class UncheckerTest {
	@Test
	public void testUncheck() throws InterruptedException {
		// 1000msのsleep中にinterruptをかけて例外を発生させる
		final Thread thread = new Thread(Unchecker.uncheck(() -> {
			System.out.println("Zzz");
			Thread.sleep(1000);
			// InterruptedExceptionが必要なくなっている
			fail("例外が発生するのでここには来ないはず");
		}));
		// threadで例外が発生したことを評価できるようにしておく
		thread.setUncaughtExceptionHandler((t, e) -> {
			// 発生したのは置き換えた例外か
			assertTrue(e instanceof RuntimeException);
			// 本当に置き換えたものならcauseにInterruptedExceptionが入っているはず
			assertTrue(e.getCause() instanceof InterruptedException);
		});
		// スタートして終わらないうちに割り込む
		thread.start();
		Thread.sleep(200);
		thread.interrupt();
	}

	@Test
	public void testUncheckWithCallable() throws InterruptedException {
		// 1000msのsleep中にinterruptをかけて例外を発生させる
		final Thread thread = new Thread(Unchecker.uncheckWithCallable(() -> {
			System.out.println("Zzz");
			Thread.sleep(1000);
			// InterruptedExceptionが必要なくなっている
			fail("例外が発生するのでここには来ないはず");
			return null; // 不必要なreturn文が必要になっている。
		}));
		// threadで例外が発生したことを評価できるようにしておく
		thread.setUncaughtExceptionHandler((t, e) -> {
			// 発生したのは置き換えた例外か
			assertTrue(e instanceof RuntimeException);
			// 本当に置き換えたものならcauseにInterruptedExceptionが入っているはず
			assertTrue(e.getCause() instanceof InterruptedException);
		});
		// スタートして終わらないうちに割り込む
		thread.start();
		Thread.sleep(200);
		thread.interrupt();
	}
}
