package ch03.ex01;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.function.Supplier;
import java.util.logging.Level;

/**
 * ロガー
 *
 * @author 山田晃一
 */
public class Logger {
	/**
	 * 出力するログレベル（これ以上のものが出力される）<br>
	 * デフォルトはLevel.INFO
	 */
	private static Level outputLowerLevel = Level.INFO;
	/**
	 * 出力先ライター<br>
	 * デフォルトはSystem.out
	 */
	private static OutputStreamWriter os = new OutputStreamWriter(System.out);

	/**
	 * 出力するレベルを設定する
	 *
	 * @param level
	 *            出力するレベル
	 */
	public static void setOutputLowerLevel(final Level level) {
		outputLowerLevel = level;
	}

	/**
	 * 出力先ライターを指定する
	 *
	 * @param outputStreamWriter
	 *            出力先ライター
	 */
	public static void setOutputStreamWriter(final OutputStreamWriter outputStreamWriter) {
		os = outputStreamWriter;
	}

	/**
	 * 指定したレベルが設定されているレベル以上であれば、メッセージを出力する。
	 *
	 * @param level
	 *            出力のレベル
	 * @param message
	 *            出力するメッセージ
	 * @throws IOException
	 *             出力先に書き込めない
	 */
	public static void logIf(final Level level, final Supplier<Boolean> criteria, final Supplier<String> message)
			throws IOException {
		if (level.intValue() >= outputLowerLevel.intValue()) {
			if (criteria.get()) {
				os.write(message.get());
			}
		}
	}
}
