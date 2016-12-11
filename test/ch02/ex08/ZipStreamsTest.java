package ch02.ex08;

import static org.junit.Assert.*;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * ZipStreamsのテストクラス
 *
 * @author 山田晃一
 */
public class ZipStreamsTest {
	@Test
	public void testZip() {
		// 0-99のストリーム
		final Stream<Integer> one = IntStream.range(0, 100).boxed();
		// 100から149のストリーム
		final Stream<Integer> two = IntStream.range(100, 150).boxed();
		// zip
		final Stream<Integer> result = ZipStreams.zip(one, two);
		final Integer[] integers = result.toArray(Integer[]::new);
		//
		assertEquals(101, integers.length);
		// 交互になったか
		for (int i = 0; i < integers.length; i++) {
			if ((i % 2) == 0) {
				assertTrue(integers[i] < 100);
			} else {
				assertTrue(integers[i] >= 100);
			}
		}
	}
}
