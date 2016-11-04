package ch01.ex02;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FilenameFilter;
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
		final String[] expectDirectories = new File("./").list(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return new File(dir, name).isDirectory();
			}
		});

		// ラムダの方を呼んでみる
		final SubDirectories subDirectories = new SubDirectories();
		final String[] directories = subDirectories.subDirectoriesByLambda("./");
		System.out.println(Arrays.toString(directories));

		// 比較する
		assertTrue(Arrays.equals(expectDirectories, directories));
	}

	@Test
	public void testSubDirectoriesByFunctionRef() {
		// 普通にisDirectoryを呼び出したのと同等の結果が得られていることを確認するため
		// 比較対象としてisDirectoryの結果を保存する
		final String[] expectDirectories = new File("./").list(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return new File(dir, name).isDirectory();
			}
		});

		// 関数参照の方を呼んでみる
		final SubDirectories subDirectories = new SubDirectories();
		final String[] directories = subDirectories.subDirectoriesByFunctionRef("./");
		System.out.println(Arrays.toString(directories));

		// 比較する
		assertTrue(Arrays.equals(expectDirectories, directories));
	}

}
