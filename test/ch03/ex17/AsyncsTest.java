package ch03.ex17;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Asyncsのテストクラス
 *
 * @author 山田晃一
 */
public class AsyncsTest {
	@Test
	public void testDoInParallelAsync() {
		Asyncs.doInParallelAsync(() -> {
		}, () -> {
		}, t -> {
			fail("handler called");
		});
	}

	@Test
	public void testDoInParallelAsyncWithFirstException() {
		final RuntimeException r = new RuntimeException("first error");
		Asyncs.doInParallelAsync(() -> {
			throw r;
		}, () -> {
		}, (t) -> {
			assertEquals(r, t);
		});
	}

	@Test
	public void testDoInParallelAsyncWithSecondException() {
		final RuntimeException r = new RuntimeException("second error");
		Asyncs.doInParallelAsync(() -> {
		}, () -> {
			throw r;
		}, (t) -> {
			assertEquals(r, t);
		});
	}

	@Test
	public void testDoInParallelAsyncWithException() {
		final RuntimeException r = new RuntimeException("error");
		Asyncs.doInParallelAsync(() -> {
			throw r;
		}, () -> {
			throw r;
		}, (t) -> {
			assertEquals(r, t);
		});
	}
}
