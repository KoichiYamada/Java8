package ch01.ex02;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

import org.junit.Test;

/**
 * SubDirectoriesのテストクラス
 *
 * @author 山田晃一
 */
public class SubDirectoriesTest {

	@Test
	public void testSubDirectoriesByLambda() {
		// 普通にisDirectoryを呼び出したのと同等の結果が得られていることを確認するため
		// 比較対象としてisDirectoryの結果を保存する
		final File[] expectDirectories = new File("./").listFiles(new FileFilter() {

			@Override
			public boolean accept(final File pathname) {
				return pathname.isDirectory();
			}
		});

		// ラムダの方を呼んでみる
		final SubDirectories subDirectories = new SubDirectories();
		final File[] directories = subDirectories.subDirectoriesByLambda("./");
		System.out.println(Arrays.toString(directories));

		// 比較する
		assertTrue(Arrays.equals(expectDirectories, directories));
	}

	@Test
	public void testSubDirectoriesByFunctionRef() {
		// 普通にisDirectoryを呼び出したのと同等の結果が得られていることを確認するため
		// 比較対象としてisDirectoryの結果を保存する
		final File[] expectDirectories = new File("./").listFiles(new FileFilter() {

			@Override
			public boolean accept(final File pathname) {
				return pathname.isDirectory();
			}
		});

		// 関数参照の方を呼んでみる
		final SubDirectories subDirectories = new SubDirectories();
		final File[] directories = subDirectories.subDirectoriesByFunctionRef("./");
		System.out.println(Arrays.toString(directories));

		// 比較する
		assertTrue(Arrays.equals(expectDirectories, directories));
	}

}
