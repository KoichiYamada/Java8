package ch04.ex05;

import java.util.function.BiFunction;
import java.util.function.Function;

import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;

/**
 * Bindingsのstaticメソッドでは複雑になる計算型プロパティをラムダ式で表現できるようにする
 *
 * @author 山田晃一
 */
public class LatientBinding {

	/**
	 * バインドするプロパティが一つの計算型プロパティを返す
	 *
	 * @param f
	 *            tの変更・無効化時に行う計算
	 * @param t
	 *            バインドするプロパティ
	 * @return 計算型プロパティ
	 */
	public static <T, R> ObservableValue<R> observe(final Function<T, R> f, final ObservableValue<T> t) {
		return new ObservableValueBase<R>() {

			{
				t.addListener((p, o, n) -> fireValueChangedEvent());
				t.addListener(o -> fireValueChangedEvent());
			}

			@Override
			public R getValue() {
				return f.apply(t.getValue());
			}
		};
	}

	/**
	 * バインドするプロパティが二つの計算型プロパティを返す
	 *
	 * @param f
	 *            t,uの変更。無効化時に行う計算
	 * @param t
	 *            バインドするプロパティ
	 * @param u
	 *            バインドするプロパティ
	 * @return 計算型プロパティ
	 */
	public static <T, U, R> ObservableValue<R> observe(final BiFunction<T, U, R> f, final ObservableValue<T> t,
			final ObservableValue<U> u) {
		return new ObservableValueBase<R>() {

			{
				t.addListener((p, o, n) -> fireValueChangedEvent());
				t.addListener(o -> fireValueChangedEvent());
				u.addListener((p, o, n) -> fireValueChangedEvent());
				u.addListener(o -> fireValueChangedEvent());
			}

			@Override
			public R getValue() {
				return f.apply(t.getValue(), u.getValue());
			}
		};
	}
}
