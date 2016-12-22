package ch01.ex09;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/*
 * Ｑ：どのような場面で、そのメソッドを活用できるでしょうか。 Ａ：条件に一致する要素からなる新しいリストを作る。
 */
/**
 * Collection2のテストクラス 兼 練習問題への回答
 *
 * @author
 */
public class Collection2Test {
    /**
     * レポート提出状況を管理する
     *
     * @author
     */
    private static class ReportHandIn {
        /**
         * 提出者名
         */
        private final String reporterName;
        /**
         * 提出状況
         */
        private boolean isHandedIn;

        /**
         * コンストラクタ
         *
         * @param reporterName
         *            提出者名
         */
        public ReportHandIn(final String reporterName) {
            this.reporterName = reporterName;
        }

        /**
         * 提出者名を取得する
         *
         * @return 提出者名
         */
        public String getReporterName() {
            return reporterName;
        }

        /**
         * 提出状況を設定する
         *
         * @param isHandIn
         *            提出状況
         */
        public void setIsHandIn(final boolean isHandIn) {
            isHandedIn = isHandIn;
        }

        /**
         * 提出状況を取得する
         *
         * @return 提出状況
         */
        public boolean isHandedIn() {
            return isHandedIn;
        }
    }

    /**
     * 提出状況のリスト<br>
     * Collection2を実装している
     *
     * @author
     */
    private static class Reports extends ArrayList<ReportHandIn>
            implements Collection2<ReportHandIn> {
    }

    @Test
    public void testForEachIf() {
        // 提出状況を生成
        final Reports reports = new Reports();
        final ReportHandIn report1 = new ReportHandIn("student1");
        final ReportHandIn report2 = new ReportHandIn("student2");
        final ReportHandIn report3 = new ReportHandIn("student3");
        final ReportHandIn report4 = new ReportHandIn("student4");
        final ReportHandIn report5 = new ReportHandIn("student5");
        report1.setIsHandIn(true);
        report3.setIsHandIn(true);
        reports.add(report1);
        reports.add(report2);
        reports.add(report3);
        reports.add(report4);
        reports.add(report5);
        // 未提出者リスト
        final List<ReportHandIn> notHandedIn = new ArrayList<>();
        // forEachIfで未提出者だけ取り出して未提出者リストへ
        reports.forEachIf(e -> {
            System.out.println(e.getReporterName() + " isn't handed in.");
            notHandedIn.add(e);
        }, e -> {
            return !e.isHandedIn();
        });
        // 正しく取り出せているかチェック
        assertEquals(3, notHandedIn.size());
        assertFalse(notHandedIn.contains(report1));
        assertTrue(notHandedIn.contains(report2));
        assertFalse(notHandedIn.contains(report3));
        assertTrue(notHandedIn.contains(report4));
        assertTrue(notHandedIn.contains(report5));
    }
}
