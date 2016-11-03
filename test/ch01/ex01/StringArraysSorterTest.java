package ch01.ex01;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * StringArraySorterのテストクラス
 *
 * @author 山田晃一
 */
public class StringArraysSorterTest {

	/**
	 * テスト対象のStringArraysSorterTest内の各メソッドが実行されたスレッドIDを保管できるように拡張したもの。
	 *
	 * @author 山田晃一
	 */
	private class StringArraysSorterStub extends StringArraysSorter {

		/**
		 * テスト本体から取り出すためのsort実行スレッドIDの仮置き場
		 */
		public long sortThreadId;

		/**
		 * テスト本体から取り出すためのcompare実行スレッドIDの仮置き場
		 */
		public long compareThreadId;

		@Override
		public void sort(String[] strings) {
			sortThreadId = Thread.currentThread().getId();
			super.sort(strings);
		}

		@Override
		public int compare(String first, String second) {
			compareThreadId = Thread.currentThread().getId();
			return super.compare(first, second);
		}
	}

	@Test
	public void testSort() {
		final String[] strings = { "one", "two", "three", "four", "five" };
		final StringArraysSorterStub stringArraysSorterStub = new StringArraysSorterStub();
		stringArraysSorterStub.sort(strings);
		final long sortThreadId = stringArraysSorterStub.sortThreadId;
		final long compareThreadId = stringArraysSorterStub.compareThreadId;

		// Arrays.sortでは同じスレッドのはず
		assertEquals(sortThreadId, compareThreadId);
	}

}
