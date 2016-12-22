package ch02.ex06;

import static org.junit.Assert.*;

import java.util.stream.Stream;

import org.junit.Test;

/**
 * CharacterStreamのテストクラス
 *
 * @author 山田晃一
 */
public class CharacterStreamTest {
    @Test
    public void testCharacterStream() {
        final Stream<Character> acctual = CharacterStream.characterStream("こんにちは");
        // 評価方法がいまいち
        assertTrue(acctual.allMatch(c -> "こんにちは".indexOf(c) != -1));
    }
}
