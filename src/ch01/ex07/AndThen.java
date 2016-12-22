package ch01.ex07;

/**
 * 二つのRunnableを順次実行するクラス
 *
 * @author 山田晃一
 */
public class AndThen {
    /**
     * メイン
     *
     * @param args
     *            引数
     */
    public static void main(final String[] args) {
        // 二つのRunnableを渡し、返されたインスタンスを実行する。
        new Thread(andThen(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(i);
            }
        }, () -> {
            for (int i = 100; i < 200; i++) {
                System.out.println(i);
            }
        })).start();
    }

    /**
     * 二つのRunnableを順次実行するRunnableを返すユーティリティメソッド
     *
     * @param first
     *            最初に実行されるRunnable
     * @param second
     *            次に実行されるRunnable
     * @return 二つのRunnableを順次実行するRunnable
     */
    public static Runnable andThen(final Runnable first, final Runnable second) {
        return () -> {
            first.run();
            second.run();
        };
    }
}
