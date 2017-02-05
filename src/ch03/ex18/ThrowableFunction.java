package ch03.ex18;

/**
 * ThrowableなFunctionのインターフェイス
 *
 * @author 山田晃一
 * @param <T>
 *            引数の型
 * @param <R>
 *            戻り値の型
 */
@FunctionalInterface
public interface ThrowableFunction<T, R> {
	/**
	 * 定義される関数
	 *
	 * @param t
	 *            引数
	 * @return R 関数の結果
	 * @throws Exception
	 *             関数の例外
	 */
	R apply(T t) throws Exception;
}
