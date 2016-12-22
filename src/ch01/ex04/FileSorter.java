package ch01.ex04;

import java.io.File;
import java.util.Arrays;

/**
 * Fileオブジェクトのソーター
 *
 * @author 山田晃一
 */
public class FileSorter {
    /**
     * 与えられたFile配列を、ディレクトリ優先の辞書順でソートする。
     *
     * @param files
     *            ソート対象
     * @return ソート済みfiles（引数と同じインスタンス）
     */
    public File[] sort(final File[] files) {
        Arrays.sort(files, (o1, o2) -> {
            // ディレクトリ同士か、ファイル同士なら辞書順
            if ((o1.isDirectory() && o2.isDirectory()) || (o1.isFile() && o2.isFile())) {
                return o1.compareTo(o2);
            }
            // o1がディレクトリならo2はファイルなので、o1が小さい
            if (o1.isDirectory()) {
                return -1;
            }
            // o1がファイルでo2がディレクトリ
            return 1;
        });
        return files;
    }
}
