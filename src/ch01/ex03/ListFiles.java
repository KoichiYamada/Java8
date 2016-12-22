package ch01.ex03;

import java.io.File;

/*
 * Ｑ：エンクロージングスコープからキャプチャされる変数はどれですか。 Ａ：下記の実装では、extention変数がキャプチャされる。
 */
/**
 * ファイルをリストアップする
 *
 * @author 山田晃一
 */
public class ListFiles {
    /**
     * 指定されたディレクトリ内で、指定された拡張子を持つファイルの配列を取得する。
     *
     * @param directory
     *            ファイルを走査するディレクトリ
     * @param extention
     *            フィルタリングする拡張子
     * @return 条件に一致したファイルの配列
     */
    public String[] listFilesWithExtention(final File directory, final String extention) {
        return directory.list((dir, name) -> name.endsWith(extention));
    }
}
