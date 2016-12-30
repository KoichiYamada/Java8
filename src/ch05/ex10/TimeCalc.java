package ch05.ex10;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAmount;

/**
 * 時間の計算
 *
 * @author 山田晃一
 */
public class TimeCalc {
    /**
     * 加算後の時刻を任意のタイムゾーンで得る
     *
     * @param from
     *            基準
     * @param add
     *            加える差分
     * @param zoneId
     *            結果を得るゾーン
     * @return 計算後の時刻
     */
    public static ZonedDateTime plus(final ZonedDateTime from, final TemporalAmount add,
            final ZoneId zoneId) {
        return from.plus(add).withZoneSameInstant(zoneId);
    }
}
