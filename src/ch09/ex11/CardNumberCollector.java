package ch09.ex11;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ユーザーのホームディレクトリからカードナンバーをリストアップ
 *
 * @author 山田晃一
 */
public class CardNumberCollector {
	public static void main(final String[] args) throws IOException, InterruptedException {
		/**
		 * <pre>
		 *  VISA, MasterCard, JCB / 4-4-4-4 / 16
		 *  American Express / 4-6-5 / 15
		 *  Diners Club / 4-6-4 / 14
		 * </pre>
		 *
		 * 下記ではまだごみを拾う可能性がある…
		 */
		final String regex = "([0-9]{4}(\\s|-)*[0-9]{4}(\\s|-)*[0-9]{4}(\\s|-)*[0-9]{4}($|[^0-9]))"
				+ "|([0-9]{4}(\\s|-)*[0-9]{6}(\\s|-)*[0-9]{5}($|[^0-9]))"
				+ "|([0-9]{4}(\\s|-)*[0-9]{6}(\\s|-)*[0-9]{4}($|[^0-9]))";

		final ProcessBuilder builder = new ProcessBuilder("grep", "-r", "-E", "\"" + regex + "\"",
				"./");
		final File home = new File(System.getProperty("user.home"));
		builder.directory(home);
		final Path tmpFile = Files.createTempFile(null, null);
		builder.redirectOutput(tmpFile.toFile());
		builder.start().waitFor();

		final Pattern p = Pattern.compile(regex);
		for (final String line : Files.readAllLines(tmpFile)) {
			final Matcher matcher = p.matcher(line);
			if (matcher.find()) {
				System.out.println(matcher.group());
			}
		}

		Files.delete(tmpFile);
	}
}
