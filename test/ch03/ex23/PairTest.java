package ch03.ex23;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Paitのテストクラス
 *
 * @author 山田晃一
 */
public class PairTest {
	@Test
	public void testMap() {
		final Pair<Integer> i = new Pair<>(1, 2);
		final Pair<String> s = i.map(String::valueOf);
		assertTrue("1".equals(s.getFirst()));
		assertTrue("2".equals(s.getSecond()));
	}
}
