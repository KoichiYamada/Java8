package ch03.ex01;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Level;

import org.junit.Test;

/**
 * Loggerのテストクラス
 *
 * @author 山田晃一
 */
public class LoggerTest {
    @Test
    public void testLogIf() throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream);
        final String expectMessage = "test log string";
        Logger.setOutputStreamWriter(outputStreamWriter);
        Logger.logIf(Level.INFO, () -> true, () -> expectMessage);
        outputStreamWriter.flush();
        // 期待したメッセージが出ること
        assertTrue(expectMessage.equals(byteArrayOutputStream.toString()));
    }

    /**
     * ログレベルが低くて、条件判定されない
     *
     * @throws IOException
     */
    @Test
    public void testLogIfLowLevel() throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream);
        final String expectMessage = "test log string";
        final boolean[] called = new boolean[1];
        Logger.setOutputStreamWriter(outputStreamWriter);
        Logger.logIf(Level.CONFIG, () -> {
            called[0] = true;
            return true;
        }, () -> {
            return expectMessage;
        });
        outputStreamWriter.flush();
        // 条件判定が呼ばれないこと
        assertFalse(called[0]);
        // メッセージが出ないこと
        assertTrue(byteArrayOutputStream.toString().isEmpty());
    }

    /**
     * ログレベルはあっているが、条件で否定
     *
     * @throws IOException
     */
    @Test
    public void testLogIfFalseCriteria() throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream);
        final String expectMessage = "test log string";
        final boolean[] called = new boolean[1];
        Logger.setOutputStreamWriter(outputStreamWriter);
        Logger.logIf(Level.INFO, () -> {
            called[0] = true;
            return false;
        }, () -> {
            return expectMessage;
        });
        outputStreamWriter.flush();
        // 条件判定が呼ばれること
        assertTrue(called[0]);
        // メッセージが出ないこと
        assertTrue(byteArrayOutputStream.toString().isEmpty());
    }
}
