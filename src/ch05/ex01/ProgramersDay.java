package ch05.ex01;

import java.time.LocalDate;
import java.time.Month;

/**
 * プログラマーの日をplusDaysを使わずに計算する（一年の２５６日目）
 *
 * @author 山田晃一
 */
public class ProgramersDay {
	/**
	 * エントリポイント
	 *
	 * @param args
	 *            引数
	 */
	public static void main(final String[] args) {
		// 実行時の年
		final int year = LocalDate.now().getYear();
		// 参考値
		System.out.println(LocalDate.of(year, Month.JANUARY, 1).plusDays(255));
		// 方法１：うるう年でなければ日付は変わらないはず
		final LocalDate date = LocalDate.of(year, Month.JANUARY, 1);
		System.out.println(LocalDate.of(year, Month.SEPTEMBER, date.isLeapYear() ? 12 : 13));
		// 方法２：地道にアタック
		int month = 0;
		int day = 0;
		for (int i = 1; i <= 12; i++) {
			final int intDayOfYear = LocalDate.of(year, i, 1).getDayOfYear();
			if (intDayOfYear >= 256) {
				break;
			}
			month = i;
			day = (256 - intDayOfYear) + 1; // intDayOfYearは朔日のカウントなので+1
		}
		System.out.println(LocalDate.of(year, month, day));
		// 方法３：足すのがダメなら引くのは？
		final int days = LocalDate.of(year, Month.JANUARY, 1).isLeapYear() ? 366 : 365;
		System.out.println(LocalDate.of(year, Month.DECEMBER, 31).minusDays(days - 256));
	}
}
