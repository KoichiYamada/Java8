package ch02.ex06;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Streamを使ったCharacterStreamの実装
 *
 * @author 山田晃一
 */
public class CharacterStream {
    /**
     * 指定された文字列をCharacterのStreamに変換する
     *
     * @param s
     *            CharacterのStreamにする文字列
     * @return CharacterのStream
     */
    public static Stream<Character> characterStream(final String s) {
        return IntStream.range(0, s.length()).mapToObj(s::charAt);
    }
}
