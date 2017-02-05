package ch06.ex09;

import java.util.Objects;

/**
 * 行列計算用クラス
 *
 * @author 山田晃一
 */
public class Matrix {
	private final int row;
	private final int col;
	private final long[][] a;

	/**
	 * コンストラクタ
	 *
	 * @param row
	 *            行数
	 * @param col
	 *            列数
	 * @param a
	 *            成分
	 * @exception IllegalArgumentException
	 *                <li>row が 0 以下の時。</li>
	 *                <li>col が 0 以下の時。</li>
	 *                <li>a が不足している時。</li>
	 * @exception NullPointerException
	 *                a が null の時。
	 */
	public Matrix(final int row, final int col, final long... a) {
		if (row < 0) {
			throw new IllegalArgumentException("row は正数でなければなりません。:" + row);
		}
		if (col < 0) {
			throw new IllegalArgumentException("col は正数でなければなりません。:" + col);
		}
		Objects.requireNonNull(a, "a は null であってはなりません。");
		if (a.length < (row * col)) {
			throw new IllegalArgumentException(
					"a.length は row * col と同じか、より大きくなければなりません。:" + a.length);
		}
		this.row = row;
		this.col = col;
		this.a = new long[row][col];
		int k = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				this.a[i][j] = a[k++];
			}
		}
	}

	/**
	 * 行列の任意の成分を取り出す。
	 *
	 * @param row
	 *            行
	 * @param col
	 *            列
	 * @return 成分
	 * @exception IndexOutOfBoundsException
	 *                row か col が範囲外の場合。
	 */
	public long get(final int row, final int col) {
		if ((row < 0) || (row >= this.row)) {
			throw new IndexOutOfBoundsException("row が範囲外です。[0-" + (this.row - 1) + "]:" + row);
		}
		if ((col < 0) || (col >= this.col)) {
			throw new IndexOutOfBoundsException("col が範囲外です。[0-" + (this.col - 1) + "]:" + col);
		}
		return a[row][col];
	}

	/**
	 * 後から成分を入れる用のプライベートコンストラクタ
	 *
	 * @param row
	 *            行数
	 * @param col
	 *            列数
	 * @exception IllegalArgumentException
	 *                <li>row が 0 以下の時。</li>
	 *                <li>col が 0 以下の時。</li>
	 */
	private Matrix(final int row, final int col) {
		if (row < 0) {
			throw new IllegalArgumentException("row は正数でなければなりません。:" + row);
		}
		if (col < 0) {
			throw new IllegalArgumentException("col は正数でなければなりません。:" + col);
		}
		this.row = row;
		this.col = col;
		a = new long[row][col];
	}

	/**
	 * 他の Matrix インスタンスと掛け算し、新しい Matrix インスタンスを返す。
	 *
	 * @param other
	 *            このインスタンスにかける Matrix
	 * @return 計算結果の Matrix
	 */
	public Matrix multiply(final Matrix other) {
		Objects.requireNonNull(other, "other は null であってはなりません。");
		if (col != other.row) {
			throw new IllegalArgumentException(
					"このインスタンスの col と other.row があっていません。:" + col + "/" + other.row);
		}
		final Matrix ret = new Matrix(row, other.col);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				for (int k = 0; k < other.col; k++) {
					ret.a[i][k] += a[i][j] * other.a[j][k];
				}
			}
		}
		return ret;
	}
}
