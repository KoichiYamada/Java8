package ch05.ex07;

import static org.junit.Assert.*;

import java.time.Instant;

import org.junit.Test;

/**
 * TimeIntervalのテストコード
 *
 * @author 山田晃一
 */
public class TimeIntervalTest {
    /**
     * コンストラクタ：正常
     */
    @Test
    public void testTimeInterval() {
        try {
            new TimeInterval(Instant.now(), Instant.now());
            new TimeInterval(Instant.now(), Instant.now().plusSeconds(1));
        } catch (final Exception e) {
            fail("例外発生：" + e);
        }
    }

    /**
     * コンストラクタ：時制が逆
     */
    @Test(expected = IllegalArgumentException.class)
    public void testTimeIntervalWithIllegalArgumentException() {
        new TimeInterval(Instant.now(), Instant.now().minusSeconds(1));
    }

    /**
     * コンストラクタ：第一引数がnull
     */
    @Test(expected = NullPointerException.class)
    public void testTimeIntervalWithNullPointerException1() {
        new TimeInterval(null, Instant.now());
    }

    /**
     * コンストラクタ：第二引数がnull
     */
    @Test(expected = NullPointerException.class)
    public void testTimeIntervalWithNullPointerException2() {
        new TimeInterval(Instant.now(), null);
    }

    /**
     * コンストラクタで渡した値が取れること
     */
    @Test
    public void testGetStart() {
        final Instant expected = Instant.now();
        final Instant actual = new TimeInterval(expected, Instant.now()).getStart();
        assertEquals(expected, actual);
    }

    /**
     * コンストラクタで渡した値が取れること
     */
    @Test
    public void testGetEnd() {
        final Instant expected = Instant.now();
        final Instant actual = new TimeInterval(expected.minusSeconds(1), expected).getEnd();
        assertEquals(expected, actual);
    }

    /**
     * 組み合わせテスト
     */
    @Test
    public void testIsOverlaps() {
        final Instant time1 = Instant.now();
        final Instant time2 = time1.plusSeconds(10);
        final Instant time3 = time1.plusSeconds(20);
        final Instant time4 = time1.plusSeconds(30);
        final TimeInterval interval1 = new TimeInterval(time1, time2);
        final TimeInterval interval2 = new TimeInterval(time3, time4);
        final TimeInterval interval3 = new TimeInterval(time1, time3);
        final TimeInterval interval4 = new TimeInterval(time2, time4);
        assertFalse(interval1.isOverlaps(interval2));
        assertTrue(interval1.isOverlaps(interval3));
        assertFalse(interval1.isOverlaps(interval4));
        assertFalse(interval2.isOverlaps(interval1));
        assertFalse(interval2.isOverlaps(interval3));
        assertTrue(interval2.isOverlaps(interval4));
        assertTrue(interval3.isOverlaps(interval1));
        assertFalse(interval3.isOverlaps(interval2));
        assertTrue(interval3.isOverlaps(interval4));
        assertFalse(interval4.isOverlaps(interval1));
        assertTrue(interval4.isOverlaps(interval2));
        assertTrue(interval4.isOverlaps(interval3));
    }

    /**
     * 引数がnull
     */
    @Test
    public void testIsOverlapsWithNullPointerException() {
        assertFalse(new TimeInterval(Instant.now(), Instant.now().plusSeconds(1)).isOverlaps(null));
    }
}
