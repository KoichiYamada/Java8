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
	public File[] subDirectoriesByLambda(final String parentDirectoryPath) {
		final File file = new File(parentDirectoryPath);
		return file.listFiles(pathname -> pathname.isDirectory());
	}

	/**
	 * 指定されたフォルダのサブディレクトリを関数参照によって列挙する
	 *
	 * @param parentDirectoryPath
	 *            サブディレクトリを列挙する親フォルダ
	 * @return サブディレクトリの配列
	 */
	public File[] subDirectoriesByFunctionRef(final String parentDirectoryPath) {
		final File file = new File(parentDirectoryPath);
		return file.listFiles(File::isDirectory);
	}
}
