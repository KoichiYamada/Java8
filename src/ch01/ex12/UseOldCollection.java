package ch01.ex12;

/*
 * Ｑ：どれだけ安全か
 * Ａ：既存の実装と同じシグニチャだとしても、既存の実装が優先されるので、既存の実装を破壊しない。
 * Ｑ：streamメソッドが古いコードのコンパイルを失敗させるシナリオ
 * Ａ：戻り値がStreamでないstreamを実装済みであればコンパイルエラーとなる
 * Ｑ：JARの古いコードは動作するか
 * Ａ：動作する（中間コードへは影響がない。実験でも動作することが確認できた）
 * 非互換性リストにも載っていない（http://www.oracle.com/technetwork/jp/java/javase/overview/8-
 * compatibility-guide-2156366-ja.html#A999081）
 */
public class UseOldCollection {
	public static void main(final String[] argv) {
		// OldCollection.javaからjarを作って、ビルドパスに追加している場合のみコンパイル可能
		// System.out.println(new OldCollection<>().stream());
	}
}
