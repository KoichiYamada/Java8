package ch01.ex03;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

import org.junit.Test;

/**
 * ListFilesのテストクラス
 *
 * @author 山田晃一
 */
public class ListFilesTest {

	@Test
	public void testListFilesWithExtention() {
		// final String directory = "./src/ch01/ex03"; // 手元のワークスペースではこれで見つかる
		final String directory = "./"; // 評価者の環境が分からないのでとりあえずカレント
		final String extention = ".java";

		// 期待する配列を取得する
		final String[] expectFiles = new File(directory).list(new FilenameFilter() {

			@Override
			public boolean accept(final File dir, final String name) {
				return name.endsWith(extention);
			}
		});

		// 実装を使って配列を取得する
		final ListFiles listFiles = new ListFiles();
		final String[] files = listFiles.listFilesWithExtention(new File(directory), extention);
		System.out.println(Arrays.toString(files));

		// 一致すること
		assertTrue(Arrays.equals(expectFiles, files));
	}

}
