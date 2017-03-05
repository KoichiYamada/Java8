package ch09.ex09;

import java.util.Objects;

/**
 * ラベル付き座標クラス。
 * <p>
 * 注：問題文に従いフィールドはfinalにはしていないが、問題と関係ないのでセッターやゲッターも実装していない。
 * </p>
 *
 * @author 山田晃一
 */
public class LabeledPoint {
	private String label;
	private int x;
	private int y;

	/**
	 * コンストラクタ
	 *
	 * @param x
	 *            X座標
	 * @param y
	 *            Y座標
	 * @param label
	 *            ラベル
	 */
	public LabeledPoint(final int x, final int y, final String label) {
		this.x = x;
		this.y = y;
		this.label = label;
	}

	/**
	 * コンストラクタ。
	 * <p>
	 * <code>LabeledPoint(x, y, null)</code>と同じ。
	 * </p>
	 *
	 * @param x
	 *            X座標
	 * @param y
	 *            Y座標
	 */
	public LabeledPoint(final int x, final int y) {
		this(x, y, null);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		final LabeledPoint other = (LabeledPoint) obj;
		return Objects.equals(label, other.label) && (x == other.x) && (y == other.y);
	}

	@Override
	public int hashCode() {
		return Objects.hash(label, x, y);
	}
}
