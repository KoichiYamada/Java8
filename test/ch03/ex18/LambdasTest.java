package ch03.ex18;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.junit.Test;

/**
 * Lambdasのテストクラス
 *
 * @author 山田晃一
 */
public class LambdasTest {
	@Test
	public void testUnchecked() {
		// これはコンパイル不可
		// Function<String, byte[]> f = t -> t.getBytes("utf8");
		//
		// これが使えるようになる
		final ThrowableFunction<String, byte[]> f = t -> t.getBytes("utf8");
		try {
			final byte[] b = f.apply("test");
			Arrays.equals("test".getBytes("utf8"), b);
		} catch (final Exception e) {
			fail("例外が発生");
		}
	}

	@Test
	public void testUncheckedWithException() {
		final ThrowableFunction<String, byte[]> f = t -> t.getBytes("foo");
		try {
			f.apply("test");
			fail("例外が発生していない");
		} catch (final Exception e) {
			assertTrue(e instanceof UnsupportedEncodingException);
		}
	}
}
