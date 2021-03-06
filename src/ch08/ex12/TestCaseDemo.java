package ch08.ex12;

public class TestCaseDemo {
    @TestCase(params = 4, expected = 24)
    @TestCase(params = 0, expected = 1)
    @TestCase(params = 1, expected = 0) // 失敗するケースを追加
    /**
     * @long the returned value
     */
    public static int factorial(final int n) {
        return n <= 1 ? 1 : n * factorial(n - 1);
    }
}
