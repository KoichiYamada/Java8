package ch01.ex06;

/**
 * チェックされる例外をthrowできるRunnableの非継承拡張
 *
 * @author 山田晃一
 */
public interface RunnableEx {
    /**
     * 実行したい処理の本体を記述するメソッド
     *
     * @throws Exception
     *             処理内で発生した例外
     */
    public void run() throws Exception;
}
