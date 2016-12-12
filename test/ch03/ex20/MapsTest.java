package ch03.ex20;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Mapsのテスト
 *
 * @author 山田晃一
 */
public class MapsTest {
	@Test
	public void testMap() {
		final List<Integer> l = Arrays.asList(1, 2, 3, 4, 5);
		final List<String> u = Maps.map(l, t -> String.valueOf(t));
		for (int i = 0; i < l.size(); i++) {
			assertTrue(l.get(i).toString().equals(u.get(i)));
		}
	}
}
