package ch05.ex10;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
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
	 * エントリポイント
	 *
	 * @param args
	 *            引数（未使用）
	 */
	public static void main(final String[] args) {
		/**
		 * ロサンジェルスからフランクフルト行き、15:05発、10時間50分のフライト
		 */
		// ロサンジェルスの出発時間
		final ZonedDateTime from = ZonedDateTime.of(LocalDate.now(), LocalTime.of(15, 5),
				ZoneId.of("America/Los_Angeles"));
		// フライト時間
		final Duration add = Duration.ofHours(10).plusMinutes(50);
		// フランクフルト到着時間
		System.out.println(TimeCalc.plus(from, add, ZoneId.of("CET")));
	}

	/**
	 * 加算後の時刻を任意のタイムゾーンで得る
	 * <p>
	 * 「このような計算を処理できるプログラム」を汎化すると二つのメソッドを呼ぶだけの単純な関数に。あってるのだろうか。
	 * </p>
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
