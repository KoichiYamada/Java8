package ch06.ex02;

/**
 * LongAdderはID生成に使えるか。
 * <p>
 * 使えない。並行更新された値の合計はsumで得る必要があるが、sumは計算中に発生した並行更新を取り逃すことがある。
 * </p>
 * 
 * <pre>
 * JavaDocより
 * public long sum()
 * 現在の合計を返します。返される値は原子的スナップショットではありません。並行更新がない場合の呼出しでは正確な結果が返されますが、合計の計算中に発生した並行更新は組み込まれない可能性があります。
 * </pre>
 *
 * @author 山田晃一
 */
public class LongAdderIdGenerator {
}
