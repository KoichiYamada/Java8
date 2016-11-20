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
		Logger.logIf(Level.INFO, () -> expectMessage);
		outputStreamWriter.flush();

		assertTrue(expectMessage.equals(byteArrayOutputStream.toString()));
	}

	@Test
	public void testLogIfLowLevel() throws IOException {
		final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream);
		final String expectMessage = "test log string";
		final boolean[] called = new boolean[1];

		Logger.setOutputStreamWriter(outputStreamWriter);
		Logger.logIf(Level.CONFIG, () -> {
			called[0] = true;
			return expectMessage;
		});
		outputStreamWriter.flush();

		assertFalse(called[0]);
		assertTrue(byteArrayOutputStream.toString().isEmpty());
	}
}
