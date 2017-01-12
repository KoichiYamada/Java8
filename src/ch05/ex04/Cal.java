package ch05.ex04;

import java.time.LocalDate;
import java.time.Month;

/**
 * カレンダーを表示する
 *
 * @author 山田晃一
 */
public class Cal {
    /**
     * 2016年1月を表示するには、
     *
     * <pre>
     * java Cal 1 2016
     * </pre>
     *
     * とする
     *
     * @param args
     *            月と年を渡すこと
     */
    public static void main(final String[] args) {
        if (args.length < 2) {
            System.out.println("USAGE: java Cal month year");
            System.out.println("  month: [0-9]");
            System.out.println("  year : [0-9]");
            return;
        }
        Cal.cal(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    }

    /**
     * カレンダーを表示する
     *
     * @param month
     *            月
     * @param year
     *            年
     */
    public static void cal(final int month, final int year) {
        final LocalDate date = LocalDate.of(year, month, 1);
        final int padding = date.getDayOfWeek().getValue();
        final boolean leap = date.isLeapYear();
        final int length = Month.of(month).length(leap);
        int col = 1;
        // 最初の週の空白
        for (; col < padding; col++) {
            System.out.print("   ");
        }
        for (int i = 1; i <= length; i++, col++) {
            System.out.print(String.format("%2d ", i));
            if ((col >= 7) || (i == length)) {
                System.out.println();
                col = 0;
            }
        }
    }
}
