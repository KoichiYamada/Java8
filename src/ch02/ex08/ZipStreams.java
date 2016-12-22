package ch02.ex08;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

/**
 * 二つのストリームを交互にマージする
 *
 * @author 山田晃一
 */
public class ZipStreams {
    /**
     * 二つのストリームを交互にマージして返す。どちらかの要素がなくなったらそこで停止する。
     *
     * @param first
     *            ストリーム１
     * @param second
     *            ストリーム２
     * @return マージされたストリーム
     */
    public static <T> Stream<T> zip(final Stream<T> first, final Stream<T> second) {
        final Builder<T> builder = Stream.builder();
        final Iterator<T> firstIterator = first.iterator();
        final Iterator<T> secondIterator = second.iterator();
        while (firstIterator.hasNext()) {
            builder.add(firstIterator.next());
            if (!secondIterator.hasNext()) {
                break;
            }
            builder.add(secondIterator.next());
        }
        return builder.build();
    }
}
