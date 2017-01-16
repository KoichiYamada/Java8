package ch06.ex09;

import java.util.Arrays;

/**
 * 行列とparallelPrefixでフィボナッチ数列を求める。
 *
 * @author 山田晃一
 */
public class Fibonacci {
    /**
     * エントリポイント
     *
     * @param args
     *            引数（未使用）
     */
    public static void main(final String[] args) {
        final Matrix[] array = new Matrix[100];
        Arrays.parallelSetAll(array, (i) -> new Matrix(2, 2, 1, 1, 1, 0));
        Arrays.parallelPrefix(array, Matrix::multiply);
        //
        for (final Matrix element : array) {
            System.out.print(element.get(0, 0) + " ");
        }
        System.out.println("");
    }
}
