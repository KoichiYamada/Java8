package ch09.ex10;

import static org.junit.Assert.*;

import org.junit.Test;

public class LabeledPointTest {
	@Test(expected = NullPointerException.class)
	public void testCompareTo() {
		final LabeledPoint p1 = new LabeledPoint(10, 20);
		final LabeledPoint p2 = new LabeledPoint(10, 20);
		p1.compareTo(p2);
	}

	@Test(expected = NullPointerException.class)
	public void testCompareTo2() {
		final LabeledPoint p1 = new LabeledPoint(10, 20);
		final LabeledPoint p2 = new LabeledPoint(10, 20);
		p2.compareTo(p1);
	}

	@Test
	public void testCompareToLabeled() {
		final LabeledPoint p1 = new LabeledPoint(10, 20, "plane a");
		final LabeledPoint p2 = new LabeledPoint(10, 20, "plane a");
		assertEquals(p1.compareTo(p2), 0);
	}

	@Test
	public void testCompareToLabeled2() {
		final LabeledPoint p1 = new LabeledPoint(10, 20, "plane a");
		final LabeledPoint p2 = new LabeledPoint(10, 20, "plane a");
		assertEquals(p2.compareTo(p1), 0);
	}

	@Test(expected = NullPointerException.class)
	public void testCompareToDifferentX() {
		final LabeledPoint p1 = new LabeledPoint(10, 20);
		final LabeledPoint p2 = new LabeledPoint(11, 20);
		p1.compareTo(p2);
	}

	@Test(expected = NullPointerException.class)
	public void testCompareToDifferentX2() {
		final LabeledPoint p1 = new LabeledPoint(10, 20);
		final LabeledPoint p2 = new LabeledPoint(11, 20);
		p2.compareTo(p1);
	}

	@Test
	public void testCompareToDifferentXLabeled() {
		final LabeledPoint p1 = new LabeledPoint(10, 20, "plane a");
		final LabeledPoint p2 = new LabeledPoint(11, 20, "plane a");
		assertTrue(p1.compareTo(p2) < 0);
	}

	@Test
	public void testCompareToDifferentXLabeled2() {
		final LabeledPoint p1 = new LabeledPoint(10, 20, "plane a");
		final LabeledPoint p2 = new LabeledPoint(11, 20, "plane a");
		assertTrue(p2.compareTo(p1) > 0);
	}

	@Test(expected = NullPointerException.class)
	public void testCompareToDifferentY() {
		final LabeledPoint p1 = new LabeledPoint(10, 20);
		final LabeledPoint p2 = new LabeledPoint(10, 21);
		p1.compareTo(p2);
	}

	@Test(expected = NullPointerException.class)
	public void testCompareToDifferentY2() {
		final LabeledPoint p1 = new LabeledPoint(10, 20);
		final LabeledPoint p2 = new LabeledPoint(10, 21);
		p2.compareTo(p1);
	}

	@Test
	public void testCompareToDifferentYLabeled() {
		final LabeledPoint p1 = new LabeledPoint(10, 20, "plane a");
		final LabeledPoint p2 = new LabeledPoint(10, 21, "plane a");
		assertTrue(p1.compareTo(p2) < 0);
	}

	@Test
	public void testCompareToDifferentYLabeled2() {
		final LabeledPoint p1 = new LabeledPoint(10, 20, "plane a");
		final LabeledPoint p2 = new LabeledPoint(10, 21, "plane a");
		assertTrue(p2.compareTo(p1) > 0);
	}

	@Test
	public void testCompareToDifferentLabeled() {
		final LabeledPoint p1 = new LabeledPoint(10, 20, "plane a");
		final LabeledPoint p2 = new LabeledPoint(10, 20, "plane b");
		assertTrue(p1.compareTo(p2) < 0);
	}

	@Test
	public void testCompareToDifferentLabeled2() {
		final LabeledPoint p1 = new LabeledPoint(10, 20, "plane a");
		final LabeledPoint p2 = new LabeledPoint(10, 20, "plane b");
		assertTrue(p2.compareTo(p1) > 0);
	}

	@Test(expected = NullPointerException.class)
	public void testCompareToDifferentXY() {
		final LabeledPoint p1 = new LabeledPoint(10, 20);
		final LabeledPoint p2 = new LabeledPoint(11, 21);
		p1.compareTo(p2);
	}

	@Test(expected = NullPointerException.class)
	public void testCompareToDifferentXY2() {
		final LabeledPoint p1 = new LabeledPoint(10, 20);
		final LabeledPoint p2 = new LabeledPoint(11, 21);
		p2.compareTo(p1);
	}

	@Test
	public void testCompareToDifferentXYLabeled() {
		final LabeledPoint p1 = new LabeledPoint(10, 20, "plane a");
		final LabeledPoint p2 = new LabeledPoint(11, 21, "plane a");
		assertTrue(p1.compareTo(p2) < 0);
	}

	@Test
	public void testCompareToDifferentXYLabeled2() {
		final LabeledPoint p1 = new LabeledPoint(10, 20, "plane a");
		final LabeledPoint p2 = new LabeledPoint(11, 21, "plane a");
		assertTrue(p2.compareTo(p1) > 0);
	}

	@Test
	public void testCompareToDifferentXAndLabel() {
		final LabeledPoint p1 = new LabeledPoint(10, 20, "plane a");
		final LabeledPoint p2 = new LabeledPoint(11, 20, "plane b");
		assertTrue(p1.compareTo(p2) < 0);
	}

	@Test
	public void testCompareToDifferentXAndLabel2() {
		final LabeledPoint p1 = new LabeledPoint(10, 20, "plane a");
		final LabeledPoint p2 = new LabeledPoint(11, 20, "plane b");
		assertTrue(p2.compareTo(p1) > 0);
	}

	@Test
	public void testCompareToDifferentYAndLabel() {
		final LabeledPoint p1 = new LabeledPoint(10, 20, "plane a");
		final LabeledPoint p2 = new LabeledPoint(10, 21, "plane b");
		assertTrue(p1.compareTo(p2) < 0);
	}

	@Test
	public void testCompareToDifferentYAndLabel2() {
		final LabeledPoint p1 = new LabeledPoint(10, 20, "plane a");
		final LabeledPoint p2 = new LabeledPoint(10, 21, "plane b");
		assertTrue(p2.compareTo(p1) > 0);
	}

	@Test
	public void testCompareToDifferentXYAndLabel() {
		final LabeledPoint p1 = new LabeledPoint(10, 20, "plane a");
		final LabeledPoint p2 = new LabeledPoint(11, 21, "plane b");
		assertTrue(p1.compareTo(p2) < 0);
	}

	@Test
	public void testCompareToDifferentXYAndLabel2() {
		final LabeledPoint p1 = new LabeledPoint(10, 20, "plane a");
		final LabeledPoint p2 = new LabeledPoint(11, 21, "plane b");
		assertTrue(p2.compareTo(p1) > 0);
	}
}
