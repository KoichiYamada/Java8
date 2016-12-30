package ch05.ex10;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Test;

/**
 * TimeCalcのテスト
 *
 * @author 山田晃一
 */
public class TimeCalcTest {
    /**
     * ロサンジェルスからフランクフルト行き、15:05発、10時間50分のフライト
     */
    @Test
    public void testPlus() {
        final ZonedDateTime from = ZonedDateTime.of(2017, 1, 20, 15, 5, 0, 0,
                ZoneId.of("America/Los_Angeles"));
        final Duration add = Duration.ofHours(10).plusMinutes(50);
        System.out.println(TimeCalc.plus(from, add, ZoneId.of("CET")));
    }
}
