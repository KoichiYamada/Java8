package ch08.ex11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Encoder;

/**
 * Basic認証
 *
 * @author 山田晃一
 */
public class BasicAuth {
	/**
	 * エントリポイント
	 *
	 * @param args
	 *            引数
	 * @throws IOException
	 *             入出力エラー
	 */
	public static void main(final String[] args) throws IOException {
		if (args.length != 3) {
			System.out.println("java BasicAuth URL USER PASS");
			return;
		}
		// printContentsWithBasicAuth("http://www.chama.ne.jp/htaccess_sample/index.htm",
		// "chama","1111");
		printContentsWithBasicAuth(args[0], args[1], args[2]);
	}

	public static void printContentsWithBasicAuth(final String uri, final String user,
			final String pass) throws IOException {
		final URL url = new URL(uri);
		final URLConnection connection = url.openConnection();
		final Encoder encoder = Base64.getEncoder();
		final String original = user + ":" + pass;
		final String encodedString = encoder
				.encodeToString(original.getBytes(StandardCharsets.UTF_8));
		connection.setRequestProperty("Authorization", "Basic " + encodedString);
		connection.connect();
		final InputStream inputStream = connection.getInputStream();
		final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			System.out.println(line);
		}
	}
}
