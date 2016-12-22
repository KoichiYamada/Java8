package ch05.ex03;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

/**
 * NextPredicateのテスト
 *
 * @author 山田晃一
 */
public class NextPredicateTest {
    @Test
    public void testNext() {
        final LocalDate today = LocalDate.now();
        final LocalDate backToWork = today
                .with(NextPredicate.next(w -> w.getDayOfWeek().getValue() < 6));
        LocalDate expected = today;
        do {
            expected = expected.plusDays(1);
        } while (!(expected.getDayOfWeek().getValue() < 6));
        System.out.println("now:" + today + " / exp:" + expected + " / acc:" + backToWork);
        assertEquals(expected, backToWork);
    }

    @Test
    public void testNextFriday() {
        final LocalDate today = LocalDate.of(2017, Month.JANUARY, 20);
        final LocalDate backToWork = today
                .with(NextPredicate.next(w -> w.getDayOfWeek().getValue() < 6));
        final LocalDate expected = LocalDate.of(2017, Month.JANUARY, 23);
        System.out.println("now:" + today + " / exp:" + expected + " / acc:" + backToWork);
        assertEquals(expected, backToWork);
    }

    @Test
    public void testNextSaturday() {
        final LocalDate today = LocalDate.of(2017, Month.JANUARY, 21);
        final LocalDate backToWork = today
                .with(NextPredicate.next(w -> w.getDayOfWeek().getValue() < 6));
        final LocalDate expected = LocalDate.of(2017, Month.JANUARY, 23);
        System.out.println("now:" + today + " / exp:" + expected + " / acc:" + backToWork);
        assertEquals(expected, backToWork);
    }

    @Test
    public void testNextSunday() {
        final LocalDate today = LocalDate.of(2017, Month.JANUARY, 22);
        final LocalDate backToWork = today
                .with(NextPredicate.next(w -> w.getDayOfWeek().getValue() < 6));
        final LocalDate expected = LocalDate.of(2017, Month.JANUARY, 23);
        System.out.println("now:" + today + " / exp:" + expected + " / acc:" + backToWork);
        assertEquals(expected, backToWork);
    }

    @Test
    public void testNextMonday() {
        final LocalDate today = LocalDate.of(2017, Month.JANUARY, 23);
        final LocalDate backToWork = today
                .with(NextPredicate.next(w -> w.getDayOfWeek().getValue() < 6));
        final LocalDate expected = LocalDate.of(2017, Month.JANUARY, 24);
        System.out.println("now:" + today + " / exp:" + expected + " / acc:" + backToWork);
        assertEquals(expected, backToWork);
    }
}
