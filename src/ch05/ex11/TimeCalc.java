package ch05.ex11;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * 時間の計算
 *
 * @author 山田晃一
 */
public class TimeCalc {
    /**
     * エントリポイント
     *
     * @param args
     *            引数（未使用）
     */
    public static void main(final String[] args) {
        /**
         * フランクフルトを14:05に出発し、ロサンジェルスに16:40につく場合、飛行時間は？
         */
        // フランクフルト出発時間
        final ZonedDateTime startFromFrankfurt = ZonedDateTime.of(LocalDate.now(),
                LocalTime.of(14, 5), ZoneId.of("CET"));
        // ロサンジェルス到着時間
        final ZonedDateTime endToLosAngeles = ZonedDateTime.of(LocalDate.now(),
                LocalTime.of(16, 40), ZoneId.of("America/Los_Angeles"));
        // フライト時間
        System.out.println(TimeCalc.between(startFromFrankfurt, endToLosAngeles));
    }

    /**
     * 二つのZonedDateTime間の時間間隔を得る
     * <p>
     * 「このような計算を処理できるプログラム」を汎化するとbetweenで足りることに。あっているのだろうか。
     * </p>
     *
     * @param start
     *            始まりの時間（含まれる）
     * @param end
     *            終わりの時間（含まれない）
     * @return 時間間隔
     */
    public static Duration between(final ZonedDateTime start, final ZonedDateTime end) {
        return Duration.between(start, end);
    }
}
