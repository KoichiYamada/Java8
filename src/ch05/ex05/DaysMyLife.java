package ch05.ex05;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;

/**
 * 自分が生きてきた日数を表示する。
 *
 * @author 山田晃一
 */
public class DaysMyLife {
	/**
	 * エントリポイント
	 *
	 * @param args
	 *            引数
	 */
	public static void main(final String[] args) {
		// 生まれた正確な時刻はちょっと分からない。
		final LocalDateTime birthDate = LocalDateTime.of(1980, Month.JANUARY, 12, 0, 0);
		final LocalDateTime now = LocalDateTime.now();
		final Duration d = Duration.between(birthDate, now);
		System.out.println(String.format("今日は生まれてから %d 日です。", d.toDays()));
	}
}
