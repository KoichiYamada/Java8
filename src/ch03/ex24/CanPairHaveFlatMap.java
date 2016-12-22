package ch03.ex24;

/*
 * 定義できない。 flatMapを定義しようとすると、<U> Pair<U> flatMap(Function<T, Pair<U>> f)となるが、
 * そのようなFunctionを定義できない。（できるが意味がない） また、定義したとして、一つのPairから二つのPairがマップされることになり、結果として 一つのPairにすることができない。
 */
public class CanPairHaveFlatMap {
}
