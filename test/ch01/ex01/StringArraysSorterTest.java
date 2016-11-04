package ch01.ex01;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
		public List<Long> compareThreadIds = Collections.synchronizedList(new ArrayList<>());

		@Override
		public String[] sort(final String[] strings) {
			sortThreadId = Thread.currentThread().getId();
			return super.sort(strings);
		}

		@Override
		public int compare(final String first, final String second) {
			compareThreadIds.add(Thread.currentThread().getId());
			return super.compare(first, second);
		}
	}

	@Test
	public void testSort() {
		final String[] strings = { "one", "two", "three", "four", "five" };
		final StringArraysSorterStub stringArraysSorterStub = new StringArraysSorterStub();
		System.out.println(Arrays.toString(stringArraysSorterStub.sort(strings)));
		final Long sortThreadId = stringArraysSorterStub.sortThreadId;
		final List<Long> compareThreadIds = stringArraysSorterStub.compareThreadIds;

		// Arrays.sortでは同じスレッドのはず
		for (final Long compareThreadId : compareThreadIds) {
			assertEquals(sortThreadId, compareThreadId);
		}
	}

}
