package ch01.ex04;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/**
 * FileSorterのテストクラス
 *
 * @author 山田晃一
 */
public class FileSorterTest {
	/**
	 * Fileのスタブ
	 *
	 * @author 山田晃一
	 */
	private class FileStub extends File {
		/**
		 * ディレクトリであるか。このクラスではディレクトリでないものはファイルとする。
		 */
		private final boolean isDirectory;

		/**
		 * コンストラクタ
		 *
		 * @param name
		 *            ファイルかディレクトリの名前
		 * @param isDirectory
		 *            ディレクトリであるか
		 */
		public FileStub(final String name, final boolean isDirectory) {
			super(name);
			this.isDirectory = isDirectory;
		}

		@Override
		public boolean isDirectory() {
			return isDirectory;
		}

		@Override
		public boolean isFile() {
			return !isDirectory;
		}
	}

	@Test
	public void testSort() {
		// スタブを使って期待通りに並んでいるリストを作る
		final List<File> expectedFileList = new ArrayList<>();
		expectedFileList.add(new FileStub("directory1", true));
		expectedFileList.add(new FileStub("directory2", true));
		expectedFileList.add(new FileStub("directory3", true));
		expectedFileList.add(new FileStub("directory4", true));
		expectedFileList.add(new FileStub("directory5", true));
		expectedFileList.add(new FileStub("file1", false));
		expectedFileList.add(new FileStub("file2", false));
		expectedFileList.add(new FileStub("file3", false));
		expectedFileList.add(new FileStub("file4", false));
		expectedFileList.add(new FileStub("file5", false));
		// シャッフルしてソートのソースとする
		final List<File> sourceFileList = new ArrayList<>(expectedFileList);
		Collections.shuffle(sourceFileList);
		// 実際に使うのはリストじゃなくて配列なので変換する
		final File[] expectedFileArray = expectedFileList.toArray(new File[expectedFileList.size()]);
		final File[] sourceFileArray = sourceFileList.toArray(new File[sourceFileList.size()]);
		System.out.println("expect  " + Arrays.toString(expectedFileArray));
		System.out.println("shuffle " + Arrays.toString(sourceFileArray));
		// シャッフルされていることを確認する
		assertFalse(Arrays.equals(expectedFileArray, sourceFileArray));
		// ソートしてみる
		final FileSorter fileSorter = new FileSorter();
		fileSorter.sort(sourceFileArray);
		System.out.println("sorted  " + Arrays.toString(sourceFileArray));
		// 期待通りに並べた配列と同じになっているはず
		assertTrue(Arrays.equals(expectedFileArray, sourceFileArray));
	}
}
