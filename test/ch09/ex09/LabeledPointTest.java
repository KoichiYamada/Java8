package ch09.ex09;

import static org.junit.Assert.*;

import org.junit.Test;

public class LabeledPointTest {
	@Test
	public void testEquals() {
		final LabeledPoint p1 = new LabeledPoint(10, 20);
		final LabeledPoint p2 = new LabeledPoint(10, 20);
		assertTrue(p1.equals(p2));
	}

	@Test
	public void testEqualsLabeled() {
		final LabeledPoint p1 = new LabeledPoint(10, 20, "plane a");
		final LabeledPoint p2 = new LabeledPoint(10, 20, "plane a");
		assertTrue(p1.equals(p2));
	}

	@Test
	public void testEqualsDifferentX() {
		final LabeledPoint p1 = new LabeledPoint(10, 20);
		final LabeledPoint p2 = new LabeledPoint(11, 20);
		assertFalse(p1.equals(p2));
	}

	@Test
	public void testEqualsDifferentXLabeled() {
		final LabeledPoint p1 = new LabeledPoint(10, 20, "plane a");
		final LabeledPoint p2 = new LabeledPoint(11, 20, "plane a");
		assertFalse(p1.equals(p2));
	}

	@Test
	public void testEqualsDifferentY() {
		final LabeledPoint p1 = new LabeledPoint(10, 20);
		final LabeledPoint p2 = new LabeledPoint(10, 21);
		assertFalse(p1.equals(p2));
	}

	@Test
	public void testEqualsDifferentYLabeled() {
		final LabeledPoint p1 = new LabeledPoint(10, 20, "plane a");
		final LabeledPoint p2 = new LabeledPoint(10, 21, "plane a");
		assertFalse(p1.equals(p2));
	}

	@Test
	public void testEqualsDifferentLabeled() {
		final LabeledPoint p1 = new LabeledPoint(10, 20, "plane a");
		final LabeledPoint p2 = new LabeledPoint(10, 20, "plane b");
		assertFalse(p1.equals(p2));
	}

	@Test
	public void testEqualsDifferentXY() {
		final LabeledPoint p1 = new LabeledPoint(10, 20);
		final LabeledPoint p2 = new LabeledPoint(11, 21);
		assertFalse(p1.equals(p2));
	}

	@Test
	public void testEqualsDifferentXYLabeled() {
		final LabeledPoint p1 = new LabeledPoint(10, 20, "plane a");
		final LabeledPoint p2 = new LabeledPoint(11, 21, "plane a");
		assertFalse(p1.equals(p2));
	}

	@Test
	public void testEqualsDifferentXAndLabel() {
		final LabeledPoint p1 = new LabeledPoint(10, 20, "plane a");
		final LabeledPoint p2 = new LabeledPoint(11, 20, "plane b");
		assertFalse(p1.equals(p2));
	}

	@Test
	public void testEqualsDifferentYAndLabel() {
		final LabeledPoint p1 = new LabeledPoint(10, 20, "plane a");
		final LabeledPoint p2 = new LabeledPoint(10, 21, "plane b");
		assertFalse(p1.equals(p2));
	}

	@Test
	public void testEqualsDifferentXYAndLabel() {
		final LabeledPoint p1 = new LabeledPoint(10, 20, "plane a");
		final LabeledPoint p2 = new LabeledPoint(11, 21, "plane b");
		assertFalse(p1.equals(p2));
	}

	@Test
	public void testHashCodeEquals() {
		final LabeledPoint p1 = new LabeledPoint(10, 20);
		final LabeledPoint p2 = new LabeledPoint(10, 20);
		assertEquals(p1.hashCode(), p2.hashCode());
	}

	@Test
	public void testHashCodeEqualsLabeled() {
		final LabeledPoint p1 = new LabeledPoint(10, 20, "plane a");
		final LabeledPoint p2 = new LabeledPoint(10, 20, "plane a");
		assertEquals(p1.hashCode(), p2.hashCode());
	}

	@Test
	public void testHashCodeDifferentX() {
		final LabeledPoint p1 = new LabeledPoint(10, 20);
		final LabeledPoint p2 = new LabeledPoint(11, 20);
		assertNotEquals(p1.hashCode(), p2.hashCode());
	}

	@Test
	public void testHashCodeDifferentXLabeled() {
		final LabeledPoint p1 = new LabeledPoint(10, 20, "plane a");
		final LabeledPoint p2 = new LabeledPoint(11, 20, "plane a");
		assertNotEquals(p1.hashCode(), p2.hashCode());
	}

	@Test
	public void testHashCodeDifferentY() {
		final LabeledPoint p1 = new LabeledPoint(10, 20);
		final LabeledPoint p2 = new LabeledPoint(10, 21);
		assertNotEquals(p1.hashCode(), p2.hashCode());
	}

	@Test
	public void testHashCodeDifferentYLabeled() {
		final LabeledPoint p1 = new LabeledPoint(10, 20, "plane a");
		final LabeledPoint p2 = new LabeledPoint(10, 21, "plane a");
		assertNotEquals(p1.hashCode(), p2.hashCode());
	}

	@Test
	public void testHashCodeDifferentLabeled() {
		final LabeledPoint p1 = new LabeledPoint(10, 20, "plane a");
		final LabeledPoint p2 = new LabeledPoint(10, 20, "plane b");
		assertNotEquals(p1.hashCode(), p2.hashCode());
	}

	@Test
	public void testHashCodeDifferentXY() {
		final LabeledPoint p1 = new LabeledPoint(10, 20);
		final LabeledPoint p2 = new LabeledPoint(11, 21);
		assertNotEquals(p1.hashCode(), p2.hashCode());
	}

	@Test
	public void testHashCodeDifferentXYLabeled() {
		final LabeledPoint p1 = new LabeledPoint(10, 20, "plane a");
		final LabeledPoint p2 = new LabeledPoint(11, 21, "plane a");
		assertNotEquals(p1.hashCode(), p2.hashCode());
	}

	@Test
	public void testHashCodeDifferentXAndLabel() {
		final LabeledPoint p1 = new LabeledPoint(10, 20, "plane a");
		final LabeledPoint p2 = new LabeledPoint(11, 20, "plane b");
		assertNotEquals(p1.hashCode(), p2.hashCode());
	}

	@Test
	public void testHashCodeDifferentYAndLabel() {
		final LabeledPoint p1 = new LabeledPoint(10, 20, "plane a");
		final LabeledPoint p2 = new LabeledPoint(10, 21, "plane b");
		assertNotEquals(p1.hashCode(), p2.hashCode());
	}

	@Test
	public void testHashCodeDifferentXYAndLabel() {
		final LabeledPoint p1 = new LabeledPoint(10, 20, "plane a");
		final LabeledPoint p2 = new LabeledPoint(11, 21, "plane b");
		assertNotEquals(p1.hashCode(), p2.hashCode());
	}
}
