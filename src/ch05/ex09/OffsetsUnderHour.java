package ch05.ex09;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoField;

/**
 * 一時間未満の情報を含むオフセットのリスト
 *
 * @author 山田晃一
 */
public class OffsetsUnderHour {
    /**
     * エントリポイント
     *
     * @param args
     *            引数（未使用）
     */
    public static void main(final String[] args) {
        final Instant now = Instant.now();
        ZoneId.getAvailableZoneIds().stream()
                .map(ZoneId::of)
                .map(now::atZone)
                .filter(s -> (s.getOffset().get(ChronoField.OFFSET_SECONDS) % 3600) != 0)
                .forEach(System.out::println);
    }
}
