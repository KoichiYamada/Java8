package ch05.ex06;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * 13日の金曜日を列挙する。
 *
 * @author 山田晃一
 */
public class Friday13th {
	/**
	 * エントリポイント
	 *
	 * @param args
	 *            引数（未使用）
	 */
	public static void main(final String[] args) {
		final TemporalAdjuster nextFriday = TemporalAdjusters.next(DayOfWeek.FRIDAY);
		LocalDate date = LocalDate.ofYearDay(1901, 1);
		while (date.getYear() <= 2000) {
			if (date.getDayOfMonth() == 13) {
				System.out.println(date);
			}
			date = date.with(nextFriday);
		}
	}
}
