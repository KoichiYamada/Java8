package ch05.ex08;

import java.time.Instant;
import java.time.ZoneId;

/**
 * 現在時刻と今日の日付のオフセットリスト
 *
 * @author 山田晃一
 */
public class Offsets {
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
                .forEach(System.out::println);
    }
}
