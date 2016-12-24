package ch05.ex07;

import java.time.Instant;
import java.util.Objects;

/**
 * 開始時刻と終了時刻をもって時間を表す
 *
 * @author 山田晃一
 */
public class TimeInterval {
    /**
     * 開始時刻
     */
    private final Instant start;
    /**
     * 終了時刻
     */
    private final Instant end;

    /**
     * コンストラクタ
     *
     * @param start
     *            開始時刻
     * @param end
     *            終了時刻
     * @throws IllegalArgumentException
     *             startがendより後を指している
     * @throws NullPointerException
     *             startかendがnull
     */
    public TimeInterval(final Instant start, final Instant end) {
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("startはendより前でなければなりません。");
        }
        this.start = Objects.requireNonNull(start, "start must not be null.");
        this.end = Objects.requireNonNull(end, "end must not be null.");
    }

    /**
     * 開始時刻を返す
     *
     * @return 開始時刻
     */
    public Instant getStart() {
        return start;
    }

    /**
     * 終了時刻を返す
     *
     * @return 終了時刻
     */
    public Instant getEnd() {
        return end;
    }

    /**
     * 示す時間が重なっているか。二つのTimeIntervalの境界時間が共有される場合は重ならないとみなされる。
     *
     * @param other
     *            比較する時間
     * @return 重なっているとtrue
     */
    public boolean isOverlaps(final TimeInterval other) {
        if (other == null) {
            return false;
        }
        return other.start.isBefore(end) && other.end.isAfter(start);
    }
}
