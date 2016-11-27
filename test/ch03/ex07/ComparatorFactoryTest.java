package ch03.ex07;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

/**
 * ComparetorFactoryのテストクラス
 *
 * @author 山田晃一
 */
public class ComparatorFactoryTest {

	@Test
	public void testCreate() {
		final Comparator<String> c = ComparatorFactory.create();
		assertEquals(0, c.compare("abc", "abc"));
		assertEquals(-1, c.compare("ab", "abc"));
		assertEquals(1, c.compare("abc", "ab"));
	}

	@Test
	public void testCreateReverseOrder() {
		final Comparator<String> c = ComparatorFactory.createReverseOrder();
		assertEquals(0, c.compare("abc", "abc"));
		assertEquals(1, c.compare("ab", "abc"));
		assertEquals(-1, c.compare("abc", "ab"));
	}

	@Test
	public void testCreateIgnoreCase() {
		final Comparator<String> c = ComparatorFactory.createIgnoreCase();
		assertEquals(0, c.compare("abc", "ABC"));
		assertEquals(-1, c.compare("ab", "aBc"));
		assertEquals(1, c.compare("abc", "aB"));
	}

	@Test
	public void testCreateIgnoreSpace() {
		final Comparator<String> c = ComparatorFactory.createIgnoreSpace();
		assertEquals(0, c.compare(" ab c", "a bc "));
		assertEquals(-1, c.compare(" a b", "ab c "));
		assertEquals(1, c.compare(" ab c", "a b "));
	}

	@Test
	public void testCreateBooleanBooleanBoolean() {
		final Comparator<String> c = ComparatorFactory.create(false, true, true);
		assertEquals(0, c.compare(" ab c", "a BC "));
		assertEquals(1, c.compare(" a b", "aB c "));
		assertEquals(-1, c.compare(" ab c", "a B "));
	}

}
