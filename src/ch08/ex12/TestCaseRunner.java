package ch08.ex12;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 山田 晃一
 */
public class TestCaseRunner {
	/**
	 * エントリポイント
	 *
	 * @param args
	 *            引数
	 *            <li>テスト対象クラス</li>
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public static void main(final String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		if (args.length < 1) {
			System.out.println("java TestCaseRunner TESTER_CLASS");
			return;
		}

		// test("ch08.ex12.TestCaseDemo");
		test(args[0]);
	}

	public static void test(final String className)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		final Class<?> clazz = Class.forName(className);
		for (final Method method : clazz.getMethods()) {
			for (final TestCase testCase : method.getAnnotationsByType(TestCase.class)) {
				System.out.print(
						"testing " + method.getName() + " with param " + testCase.params());
				final int ret = (int) method.invoke(null, testCase.params());
				if (ret == testCase.expected()) {
					System.out.println(" : SUCCESS");
				} else {
					System.out.println(
							" : FAILURE : expected " + testCase.expected() + " but actually "
									+ ret);
				}
			}
		}
		System.out.println("end");
	}
}
