package ch08.ex01;

/**
 * int値と符号なしの加算、減算、除算、比較
 * <p>
 * devideUnsignedとremainderUnsignedが必要な理由 → 無いと正しい計算が出来ないから。*1
 * </p>
 *
 * @author 山田晃一
 */
public class UnsignedOperation {
	/**
	 * エントリポイント
	 *
	 * @param args
	 *            引数（未使用）
	 */
	public static void main(final String[] args) {
		final int a = Integer.MAX_VALUE;
		final int b = Integer.MAX_VALUE + Integer.MAX_VALUE;

		System.out.println("加算(a + a)");
		System.out.println("int      :" + (a + a));
		System.out.println("unsigned :" + Integer.toUnsignedString(a + a));
		System.out.println();

		System.out.println("減算(b - 5)");
		System.out.println("int      :" + (b - 5));
		System.out.println("unsigned :" + Integer.toUnsignedString(b - 5));
		System.out.println();

		System.out.println("除算(b / 5)");
		System.out.println("int      :" + (b / 5));
		System.out.println("unsigned :" + Integer.divideUnsigned(b, 5)); // *1
		System.out.println();

		System.out.println("剰余(b % 5)");
		System.out.println("int      :" + (b % 5));
		System.out.println("unsigned :" + Integer.remainderUnsigned(b, 5)); // *1
		System.out.println();

		System.out.println("Integer.compare(a, b)");
		System.out.println("int      :" + Integer.compare(a, b));
		System.out.println("unsigned :" + Integer.compareUnsigned(a, b));
		System.out.println();
	}
}
