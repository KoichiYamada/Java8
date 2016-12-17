package ch05.ex02;

import java.time.LocalDate;
import java.time.Month;

/**
 * 2000/2/29の一年後、四年後、一年後を四回でどうなるか。
 *
 * @author 山田晃一
 */
public class PlusYears {
	public static void main(final String[] args) {
		// 基準
		final LocalDate base = LocalDate.of(2000, Month.FEBRUARY, 29);
		System.out.println(base);
		// ◆一年後（うるう年ではないので、その月の最後の日となる）
		System.out.println(base.plusYears(1));
		// ◆四年後（うるう年なので、そのまま）
		System.out.println(base.plusYears(4));
		// ◆一年後を四回（一年後の段階で28日となり、以降の３回はそのまま）
		System.out.println(base.plusYears(1).plusYears(1).plusYears(1).plusYears(1));
	}
}
