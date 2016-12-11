package ch03.ex09;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/**
 * Comparatorsのテストクラス
 *
 * @author 山田晃一
 */
public class ComparatorsTest {
	private static class TestObj {
		public final int number;
		public final String name;
		public final int value;

		public TestObj(final int number, final String name, final int value) {
			this.number = number;
			this.name = name;
			this.value = value;
		}
	}

	private final TestObj o1 = new TestObj(10, "1", 10);
	private final TestObj o2 = new TestObj(10, "1", 20);
	private final TestObj o3 = new TestObj(10, "2", 5);
	private final TestObj o4 = new TestObj(20, "0", 0);
	private final List<TestObj> list = Arrays.asList(o1, o2, o3, o4);

	@Test
	public void testLexicographicComparatorNumberNameValue() {
		System.out.println("testLexicographicComparatorNumberNameValue");
		final TestObj[] expect = new TestObj[] { o1, o2, o3, o4 };
		Collections.shuffle(list);
		list.stream().forEach(e -> System.out.print(e.number + ":" + e.name + ":" + e.value + System.lineSeparator()));
		System.out.println("--↓--↓--↓--↓--↓--↓--↓--↓--↓--↓--");
		list.sort(Comparators.lexicographicComparator("number", "name", "value"));
		list.stream().forEach(e -> System.out.print(e.number + ":" + e.name + ":" + e.value + System.lineSeparator()));
		assertTrue(Arrays.deepEquals(expect, list.toArray()));
	}

	@Test
	public void testLexicographicComparatorNumberValueName() {
		System.out.println("testLexicographicComparatorNumberValueName");
		final TestObj[] expect = new TestObj[] { o3, o1, o2, o4 };
		Collections.shuffle(list);
		list.stream().forEach(e -> System.out.print(e.number + ":" + e.name + ":" + e.value + System.lineSeparator()));
		System.out.println("--↓--↓--↓--↓--↓--↓--↓--↓--↓--↓--");
		list.sort(Comparators.lexicographicComparator("number", "value", "name"));
		list.stream().forEach(e -> System.out.print(e.number + ":" + e.name + ":" + e.value + System.lineSeparator()));
		assertTrue(Arrays.deepEquals(expect, list.toArray()));
	}
}
