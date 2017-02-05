package ch06.ex10;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * webページの全てのリンクを表示する。
 *
 * @author 山田晃一
 */
public class PrintLink {
	/**
	 * キーボード入力用
	 */
	private static Scanner stdin = new Scanner(System.in);

	/**
	 * エントリポイント
	 *
	 * @param args
	 *            引数（未使用）
	 */
	public static void main(final String[] args) {
		PrintLink.getURLInput("URLを入力してください。：").thenCompose(PrintLink::readPage)
				.thenApply(PrintLink::getLinks).thenAccept(System.out::println);
		ForkJoinPool.commonPool().awaitQuiescence(10, TimeUnit.SECONDS);
	}

	/**
	 * ユーザーの入力を受け付ける。
	 *
	 * @param prompt
	 *            ユーザーに提示する文字列。
	 * @return 将来入手可能なURL
	 * @exception RuntimeException
	 *                ユーザー入力がURLオブジェクトに変換できない時。
	 */
	public static CompletableFuture<URL> getURLInput(final String prompt) {
		return CompletableFuture.supplyAsync(() -> {
			System.out.print(prompt);
			final String line = stdin.nextLine();
			try {
				return new URL(line);
			} catch (final MalformedURLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		});
	}

	/**
	 * URLからwebページを取得する。
	 *
	 * @param url
	 *            取得するwebページの在処。
	 * @return 将来入手可能なページの内容。
	 * @exception RuntimeException
	 *                URLからの読み込みに失敗した時。
	 */
	public static CompletableFuture<String> readPage(final URL url) {
		return CompletableFuture.supplyAsync(() -> {
			try (Scanner scanner = new Scanner(url.openStream())) {
				final StringBuilder builder = new StringBuilder();
				while (scanner.hasNextLine()) {
					builder.append(scanner.nextLine()).append(System.lineSeparator());
				}
				return builder.toString();
			} catch (final IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		});
	}

	/**
	 * リンクをリストアップする。
	 *
	 * @param page
	 *            リンクを探すHTML。
	 * @return リンクのリスト。
	 */
	public static List<String> getLinks(final String page) {
		final String href = "<a href=\"([^\"]+)\".*?>(.*?)</a>";
		final Pattern p = Pattern.compile(href,
				Pattern.DOTALL | Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
		final Matcher m = p.matcher(page);
		final List<String> links = new ArrayList<>();
		while (m.find()) {
			links.add(m.group(2) + "( " + m.group(1) + " )");
		}
		return links;
	}
}
