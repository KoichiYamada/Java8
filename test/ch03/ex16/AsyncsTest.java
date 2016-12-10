package ch03.ex16;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Asyncsのテスト
 * @author 山田晃一
 */
public class AsyncsTest {

	@Test
	public void testDoInOrderAsync() {
		Asyncs.doInOrderAsync(() -> Integer.valueOf(5), (i, t) -> {
			assertEquals(5, i.intValue());
			assertNull(t);
		}, t -> fail("handler called"));
	}

	@Test
	public void testDoInOrderAsyncWithFirstException() {
		final RuntimeException r = new RuntimeException("first runtime error");
		Asyncs.doInOrderAsync(() -> {
			throw r;
		}, (i, t) -> {
			assertNull(i);
			assertEquals(r, t);
		}, t -> fail("handler called"));
	}

	@Test
	public void testDoInOrderAsyncWithSecondException() {
		final RuntimeException r = new RuntimeException("second runtime error");
		Asyncs.doInOrderAsync(() -> Integer.valueOf(5), (i, t) -> {
			assertEquals(5, i.intValue());
			assertNull(t);
			throw r;
		}, t -> assertEquals(r, t));
	}

	@Test
	public void testDoInOrderAsyncWithException() {
		final RuntimeException r1 = new RuntimeException("first runtime error");
		final RuntimeException r2 = new RuntimeException("second runtime error");
		Asyncs.doInOrderAsync(() -> {
			throw r1;
		}, (i, t) -> {
			assertNull(i);
			assertEquals(r1, t);
			throw r2;
		}, t -> assertEquals(r2, t));
	}
}
