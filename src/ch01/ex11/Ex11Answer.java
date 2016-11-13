package ch01.ex11;

/**
 * Ex11回答用クラス
 *
 * @author 山田晃一
 */
public class Ex11Answer {

	/**
	 * <tt>
	 * abstruct / abstruct
	 * abstract / default
	 * abstract / static
	 * default / default
	 * default / static
	 * static / static
	 * </tt>
	 */

	public class IaJa implements IAbstractF, JAbstractF {

		@Override
		public void f() {
			// オーバーライドが必要
			// もしくは、IaJaを抽象クラスにする
		}
	}

	public class IaJd implements IAbstractF, JDefaultF {

		@Override
		public void f() {
			// オーバーライドが必要
			// JDefaultF.super.FとしてJDefaultFのデフォルト実装を呼ぶこともできる
		}
	}

	public class IaJs implements IAbstractF, JStaticF {

		@Override
		public void f() {
			// オーバーライドが必要
			// もしくは、ASを抽象クラスにする
			// staticメソッドはサブタイプに影響を与えない
		}
	}

	public class IdJd implements IDefaultF, JDefaultF {

		@Override
		public void f() {
			// オーバーライドが必要
			// IDefaultF.super.FとしてIDefaultFのデフォルト実装を呼ぶこともできる
			// JDefaultF.super.FとしてJDefaultFのデフォルト実装を呼ぶこともできる
		}
	}

	public class IdJs implements IDefaultF, JStaticF {
		// IDefaultFのdefaultが生きており、使うかどうかは実装者次第
		// staticメソッドはサブタイプに影響を与えない
	}

	public class IsJs implements IStaticF, JStaticF {
		// staticメソッドが被ってもサブタイプには影響しない
	}

	/**
	 * <tt>
	 * クラスにデフォルトは無いので単にインスタンスメソッドとする。
	 * abstract / abstract
	 * abstract / default
	 * abstract / static
	 * instance / abstract
	 * instance / default
	 * instance / static
	 * static / abstract
	 * static / default
	 * static / static
	 * </tt>
	 */

	public class SaIa extends SAbstractF implements IAbstractF {

		@Override
		public void f() {
			// クラス優先なのでSAbstractFのfが生きる
			// fを実装するか、SaIaを抽象クラスにする必要がある
		}
	}

	public class SaId extends SAbstractF implements IDefaultF {

		@Override
		public void f() {
			// クラス優先なのでSAbstractFのfが生きる
			// fを実装するか、SaIaを抽象クラスにする必要がある
		}
	}

	public class SaIs extends SAbstractF implements IStaticF {

		@Override
		public void f() {
			// ここではInterfaceのstaticメソッドの影響は無いので、
			// 単に抽象クラスを継承しただけ
		}
	}

	public class SdIa extends SDefaultF implements IAbstractF {
		// SDefaultFのfがIAbstractFのfを実装していることになり何もしなくてもよい
		// オーバーロードしてもよい
	}

	public class SdId extends SDefaultF implements IDefaultF {
		// クラス優先によりIDefaultFのdefaultは単に無視される
	}

	public class SdIs extends SDefaultF implements IStaticF {
		// staticメソッドはサブタイプに影響しない
	}

	// public class SsIa extends SStaticF implements IAbstractF {
	// コンパイルエラー
	// staticメソッドとinterfaceのメソッドが競合する
	// }

	// public class SsId extends SStaticF implements IDefaultF {
	// コンパイルエラー
	// staticメソッドとinterfaceのメソッドが競合する
	// }

	public class SsIs extends SStaticF implements IStaticF {
		// staticメソッドが被ってもサブタイプには影響しない
	}
}
