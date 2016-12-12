package ch03.ex19;

/**
 * <pre>
 * <U> U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner)
 * BiFunctionの最初の型引数は ? super U とすべきか。
 *
 * すべきではない。以下の二つの契約を守れなくなるから。
 * １．
 * U result = identity;
 * for (T element : this stream)
 *     result = accumulator.apply(result, element) ←ダウンキャスト
 * return result;
 * ２．
 * combiner.apply(u, accumulator.apply(identity, t)) == accumulator.apply(u, t) ←左辺第二引数がダウンキャスト
 * </pre>
 */
public class BiFunctionFirstArg {
}
