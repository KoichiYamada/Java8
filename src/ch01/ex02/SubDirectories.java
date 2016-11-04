package ch01.ex02;

import java.io.File;

/**
 * 指定されたフォルダのサブディレクトリを色々な方法で列挙する
 *
 * @author 山田晃一
 */
public class SubDirectories {

	/**
	 * 指定されたフォルダのサブディレクトリをラムダによって列挙する
	 *
	 * @param parentDirectoryPath
	 *            サブディレクトリを列挙する親フォルダ
	 * @return サブディレクトリの配列
	 */
	public String[] subDirectoriesByLambda(final String parentDirectoryPath) {
		final File file = new File(parentDirectoryPath);
		return file.list((dir, name) -> {
			return new File(dir, name).isDirectory();
		});
	}

	/**
	 * 指定されたフォルダのサブディレクトリを関数参照によって列挙する
	 *
	 * @param parentDirectoryPath
	 *            サブディレクトリを列挙する親フォルダ
	 * @return サブディレクトリの配列
	 */
	public String[] subDirectoriesByFunctionRef(final String parentDirectoryPath) {
		final File file = new File(parentDirectoryPath);
		return file.list(this::isDirectory);
	}

	/**
	 * 指定されたアイテムがディレクトリか判定する
	 *
	 * @param dir
	 *            アイテムのあるディレクトリ
	 * @param name
	 *            アイテムの名前
	 * @return ディレクトリであるか
	 */
	private boolean isDirectory(final File dir, final String name) {
		return new File(dir, name).isDirectory();
	}
}
