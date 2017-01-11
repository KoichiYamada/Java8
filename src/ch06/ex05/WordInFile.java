package ch06.ex05;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 複数のスレッドで複数のファイルから単語を読み込んで、単語ごとに使われているファイルを調べる。
 *
 * @author 山田晃一
 */
public class WordInFile {
    /**
     * データファイル1のパス
     */
    private static String DATA_FILE1_PATH = "src/ch06/ex05/alice.txt";
    /**
     * データファイル2のパス
     */
    private static String DATA_FILE2_PATH = "src/ch06/ex05/war-and-peace.txt";
    /**
     * スレッド終了待ちタイムアウト
     */
    private static int TIMEOUT_MINUTE = 3;
    /**
     * 集計するマップ
     */
    public final ConcurrentHashMap<String, Set<File>> map = new ConcurrentHashMap<>();

    /**
     * エントリポイント
     *
     * @param args
     *            引数（未使用）
     * @throws InterruptedException
     *             スレッド終了町中に割り込み発生
     */
    public static void main(final String[] args) throws InterruptedException {
        final ExecutorService executorService = Executors
                .newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        final WordInFile wordInFile = new WordInFile();
        executorService.execute(() -> {
            wordInFile.read(DATA_FILE1_PATH);
        });
        executorService.execute(() -> {
            wordInFile.read(DATA_FILE2_PATH);
        });
        executorService.shutdown();
        executorService.awaitTermination(TIMEOUT_MINUTE, TimeUnit.MINUTES);
        /*
         * 確認用にちょっとだけ覗いてみる
         */
        wordInFile.map.entrySet().stream().limit(10).forEach(e -> {
            System.out.println("word:" + e.getKey() + " in ");
            e.getValue().forEach((f) -> {
                System.out.println("  file:" + f.getPath());
            });
        });
    }

    /**
     * ファイルを開いて単語を読み出してマップに追加する
     *
     * @param filePath
     *            データファイルパス
     */
    private void read(final String filePath) {
        try {
            final String contents = new String(Files.readAllBytes(Paths.get(filePath)));
            final List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
            words.parallelStream().forEach(word -> {
                final File file = new File(filePath);
                final Set<File> set = new HashSet<>();
                set.add(file);
                map.merge(word, set, (e, n) -> {
                    e.addAll(n);
                    return e;
                });
            });
        } catch (final IOException e1) {
            e1.printStackTrace();
        }
    }
}
