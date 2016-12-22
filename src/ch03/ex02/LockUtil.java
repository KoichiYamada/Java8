package ch03.ex02;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLockのlockとunlockをラップしたメソッドを提供するユーティリティクラス
 *
 * @author 山田晃一
 */
public class LockUtil {
    /**
     * リエントラントロック／アンロックを処理の前後に行うユーティリティメソッド
     *
     * @param lockObj
     *            リエントラントロックオブジェクト
     * @param runnable
     *            実行する処理
     */
    public static void withLock(final ReentrantLock lockObj, final Runnable runnable) {
        lockObj.lock();
        try {
            runnable.run();
        } finally {
            lockObj.unlock();
        }
    }
}
