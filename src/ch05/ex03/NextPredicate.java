package ch05.ex03;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.function.Predicate;

/**
 * Predicate<LocalDate>を受け取るnextの実装
 *
 * @author 山田晃一
 */
public class NextPredicate {
	/**
	 * 次に条件にあうLocalDateを得る
	 *
	 * @param predicate
	 *            条件
	 * @return 次に条件にあうLocalDate
	 */
	public static TemporalAdjuster next(Predicate<LocalDate> predicate) {
		return TemporalAdjusters.ofDateAdjuster(w -> {
			LocalDate ret = w;
			do {
				ret = ret.plusDays(1);
			} while (!predicate.test(ret));
			return ret;
		});
	}
}
