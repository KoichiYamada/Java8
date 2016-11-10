package ch01.ex09;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * コレクション２ 指定された名前なので２は仕方がない。
 *
 * @author 山田晃一
 *
 * @param <T>
 *            このコレクションが保持する要素の型
 */
public interface Collection2<T> extends Collection<T> {

	/**
	 * filterがtrueを返す要素にactionを適用する
	 *
	 * @param action
	 *            適用するアクション
	 * @param filter
	 *            アクションを適用すべき要素にtrueを返すフィルター
	 */
	default void forEachIf(Consumer<T> action, Predicate<T> filter) {
		final Iterator<T> ite = iterator();
		while (ite.hasNext()) {
			final T elm = ite.next();
			if (filter.test(elm)) {
				action.accept(elm);
			}
		}
	}
}
