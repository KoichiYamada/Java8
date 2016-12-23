package ch05.ex04;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Month;

import org.junit.Test;

/**
 * Calのテストクラス
 *
 * @author 山田晃一
 */
public class CalTest {
    @Test
    public void testCalJanuary2016() {
        final ByteArrayOutputStream o = new ByteArrayOutputStream();
        System.setOut(new PrintStream(o));
        final StringBuilder sb = new StringBuilder();
        sb.append("             1  2  3 ").append(System.lineSeparator());
        sb.append(" 4  5  6  7  8  9 10 ").append(System.lineSeparator());
        sb.append("11 12 13 14 15 16 17 ").append(System.lineSeparator());
        sb.append("18 19 20 21 22 23 24 ").append(System.lineSeparator());
        sb.append("25 26 27 28 29 30 31 ").append(System.lineSeparator());
        Cal.cal(Month.JANUARY.getValue(), 2016);
        assertEquals(sb.toString(), o.toString());
    }

    @Test
    public void testCalFebruary2016() {
        final ByteArrayOutputStream o = new ByteArrayOutputStream();
        System.setOut(new PrintStream(o));
        final StringBuilder sb = new StringBuilder();
        sb.append(" 1  2  3  4  5  6  7 ").append(System.lineSeparator());
        sb.append(" 8  9 10 11 12 13 14 ").append(System.lineSeparator());
        sb.append("15 16 17 18 19 20 21 ").append(System.lineSeparator());
        sb.append("22 23 24 25 26 27 28 ").append(System.lineSeparator());
        sb.append("29 ").append(System.lineSeparator());
        Cal.cal(Month.FEBRUARY.getValue(), 2016);
        assertEquals(sb.toString(), o.toString());
    }
}
