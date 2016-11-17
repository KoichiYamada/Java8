package ch02.ex09;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

/**
 * PseudoFlatMapのテストクラス
 *
 * @author 山田晃一
 */
public class PseudoFlatMapTest {

	@Test
	public void testFlat1() {
		// 通常系
		final ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(new Integer[] { 0, 1, 2, 3, 4 }));
		final ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(new Integer[] { 5, 6, 7, 8, 9 }));
		final ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(new Integer[] { 10, 11, 12, 13, 14 }));
		final ArrayList<ArrayList<Integer>> lists = new ArrayList<>(Arrays.asList(list1, list2, list3));

		final ArrayList<Integer> result = PseudoFlatMap.flat1(lists.stream());

		for (int i = 0; i < result.size(); i++) {
			assertEquals(i, result.get(i).intValue());
		}
	}

	@Test
	public void testFlat1WithEmptyList() {
		// 空のリストが含まれる場合
		final ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(new Integer[] { 0, 1, 2, 3, 4 }));
		final ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(new Integer[] {}));
		final ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(new Integer[] { 5, 6, 7, 8, 9 }));
		final ArrayList<ArrayList<Integer>> lists = new ArrayList<>(Arrays.asList(list1, list2, list3));

		final ArrayList<Integer> result = PseudoFlatMap.flat1(lists.stream());

		for (int i = 0; i < result.size(); i++) {
			assertEquals(i, result.get(i).intValue());
		}
	}

	@Test
	public void testFlat1WithNullA() {
		// 最初の要素がnull
		final ArrayList<Integer> list1 = null;
		final ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(new Integer[] { 0, 1, 2, 3, 4 }));
		final ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(new Integer[] { 5, 6, 7, 8, 9 }));
		final ArrayList<ArrayList<Integer>> lists = new ArrayList<>(Arrays.asList(list1, list2, list3));

		final ArrayList<Integer> result = PseudoFlatMap.flat1(lists.stream());

		for (int i = 0; i < result.size(); i++) {
			assertEquals(i, result.get(i).intValue());
		}
	}

	@Test
	public void testFlat1WithNullB() {
		// 中間の要素がnull
		final ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(new Integer[] { 0, 1, 2, 3, 4 }));
		final ArrayList<Integer> list2 = null;
		final ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(new Integer[] { 5, 6, 7, 8, 9 }));
		final ArrayList<ArrayList<Integer>> lists = new ArrayList<>(Arrays.asList(list1, list2, list3));

		final ArrayList<Integer> result = PseudoFlatMap.flat1(lists.stream());

		for (int i = 0; i < result.size(); i++) {
			assertEquals(i, result.get(i).intValue());
		}
	}

	@Test
	public void testFlat1WithNullC() {
		// 最後の要素がnull
		final ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(new Integer[] { 0, 1, 2, 3, 4 }));
		final ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(new Integer[] { 5, 6, 7, 8, 9 }));
		final ArrayList<Integer> list3 = null;
		final ArrayList<ArrayList<Integer>> lists = new ArrayList<>(Arrays.asList(list1, list2, list3));

		final ArrayList<Integer> result = PseudoFlatMap.flat1(lists.stream());

		for (int i = 0; i < result.size(); i++) {
			assertEquals(i, result.get(i).intValue());
		}
	}

	@Test
	public void testFlat2() {
		// 通常系
		final ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(new Integer[] { 0, 1, 2, 3, 4 }));
		final ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(new Integer[] { 5, 6, 7, 8, 9 }));
		final ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(new Integer[] { 10, 11, 12, 13, 14 }));
		final ArrayList<ArrayList<Integer>> lists = new ArrayList<>(Arrays.asList(list1, list2, list3));

		final ArrayList<Integer> result = PseudoFlatMap.flat2(lists.stream());

		for (int i = 0; i < result.size(); i++) {
			assertEquals(i, result.get(i).intValue());
		}
	}

	@Test
	public void testFlat2WithEmptyList() {
		// 空のリストが含まれる場合
		final ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(new Integer[] { 0, 1, 2, 3, 4 }));
		final ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(new Integer[] {}));
		final ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(new Integer[] { 5, 6, 7, 8, 9 }));
		final ArrayList<ArrayList<Integer>> lists = new ArrayList<>(Arrays.asList(list1, list2, list3));

		final ArrayList<Integer> result = PseudoFlatMap.flat2(lists.stream());

		for (int i = 0; i < result.size(); i++) {
			assertEquals(i, result.get(i).intValue());
		}
	}

	@Test
	public void testFlat2WithNullA() {
		// 最初の要素がnull
		final ArrayList<Integer> list1 = null;
		final ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(new Integer[] { 0, 1, 2, 3, 4 }));
		final ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(new Integer[] { 5, 6, 7, 8, 9 }));
		final ArrayList<ArrayList<Integer>> lists = new ArrayList<>(Arrays.asList(list1, list2, list3));

		final ArrayList<Integer> result = PseudoFlatMap.flat2(lists.stream());

		for (int i = 0; i < result.size(); i++) {
			assertEquals(i, result.get(i).intValue());
		}
	}

	@Test
	public void testFlat2WithNullB() {
		// 中間の要素がnull
		final ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(new Integer[] { 0, 1, 2, 3, 4 }));
		final ArrayList<Integer> list2 = null;
		final ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(new Integer[] { 5, 6, 7, 8, 9 }));
		final ArrayList<ArrayList<Integer>> lists = new ArrayList<>(Arrays.asList(list1, list2, list3));

		final ArrayList<Integer> result = PseudoFlatMap.flat2(lists.stream());

		for (int i = 0; i < result.size(); i++) {
			assertEquals(i, result.get(i).intValue());
		}
	}

	@Test
	public void testFlat2WithNullC() {
		// 最後の要素がnull
		final ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(new Integer[] { 0, 1, 2, 3, 4 }));
		final ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(new Integer[] { 5, 6, 7, 8, 9 }));
		final ArrayList<Integer> list3 = null;
		final ArrayList<ArrayList<Integer>> lists = new ArrayList<>(Arrays.asList(list1, list2, list3));

		final ArrayList<Integer> result = PseudoFlatMap.flat2(lists.stream());

		for (int i = 0; i < result.size(); i++) {
			assertEquals(i, result.get(i).intValue());
		}
	}

	@Test
	public void testFlat3() {
		// 正常系
		final ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(new Integer[] { 0, 1, 2, 3, 4 }));
		final ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(new Integer[] { 5, 6, 7, 8, 9 }));
		final ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(new Integer[] { 10, 11, 12, 13, 14 }));
		final ArrayList<ArrayList<Integer>> lists = new ArrayList<>(Arrays.asList(list1, list2, list3));

		final ArrayList<Integer> result = PseudoFlatMap.flat3(lists.stream());

		for (int i = 0; i < result.size(); i++) {
			assertEquals(i, result.get(i).intValue());
		}
	}

	@Test
	public void testFlat3WithEmptyList() {
		// 空のリストが含まれる場合
		final ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(new Integer[] { 0, 1, 2, 3, 4 }));
		final ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(new Integer[] {}));
		final ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(new Integer[] { 5, 6, 7, 8, 9 }));
		final ArrayList<ArrayList<Integer>> lists = new ArrayList<>(Arrays.asList(list1, list2, list3));

		final ArrayList<Integer> result = PseudoFlatMap.flat3(lists.stream());

		for (int i = 0; i < result.size(); i++) {
			assertEquals(i, result.get(i).intValue());
		}
	}

	@Test
	public void testFlat3WithNullA() {
		// 最初の要素がnull
		final ArrayList<Integer> list1 = null;
		final ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(new Integer[] { 0, 1, 2, 3, 4 }));
		final ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(new Integer[] { 5, 6, 7, 8, 9 }));
		final ArrayList<ArrayList<Integer>> lists = new ArrayList<>(Arrays.asList(list1, list2, list3));

		final ArrayList<Integer> result = PseudoFlatMap.flat3(lists.stream());

		for (int i = 0; i < result.size(); i++) {
			assertEquals(i, result.get(i).intValue());
		}
	}

	@Test
	public void testFlat3WithNullB() {
		// 中間の要素がnull
		final ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(new Integer[] { 0, 1, 2, 3, 4 }));
		final ArrayList<Integer> list2 = null;
		final ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(new Integer[] { 5, 6, 7, 8, 9 }));
		final ArrayList<ArrayList<Integer>> lists = new ArrayList<>(Arrays.asList(list1, list2, list3));

		final ArrayList<Integer> result = PseudoFlatMap.flat3(lists.stream());

		for (int i = 0; i < result.size(); i++) {
			assertEquals(i, result.get(i).intValue());
		}
	}

	@Test
	public void testFlat3WithNullC() {
		// 最後の要素がnull
		final ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(new Integer[] { 0, 1, 2, 3, 4 }));
		final ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(new Integer[] { 5, 6, 7, 8, 9 }));
		final ArrayList<Integer> list3 = null;
		final ArrayList<ArrayList<Integer>> lists = new ArrayList<>(Arrays.asList(list1, list2, list3));

		final ArrayList<Integer> result = PseudoFlatMap.flat3(lists.stream());

		for (int i = 0; i < result.size(); i++) {
			assertEquals(i, result.get(i).intValue());
		}
	}
}
