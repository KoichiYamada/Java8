package ch08.ex08;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * CheckedQueue の利点
 *
 * @author 山田晃一
 */
public class AdvanceOfCheckedQueue {
	/**
	 * エントリポイント
	 *
	 * @param args
	 *            引数（未使用）
	 */
	public static void main(final String[] args) {
		final Queue<String> queue = new LinkedList<>();
		final Queue<String> checkedQueue = Collections.checkedQueue(queue, String.class);

		legacyMethod(queue);
		System.out.println("if 1 visible to you it is a pity. -> " + queue.size());

		try {
			legacyMethod(checkedQueue);
		} catch (final ClassCastException e) {
			System.out.println(
					"ClassCastException has happened. this is the advantage of checkedQueue.");
		}
	}

	/**
	 * 古い関数。例えばライブラリの関数など。
	 *
	 * @param queue
	 *            raw型のキュー。
	 */
	@SuppressWarnings("unchecked")
	public static void legacyMethod(@SuppressWarnings("rawtypes") final Queue queue) {
		queue.add(3);
	}
}
