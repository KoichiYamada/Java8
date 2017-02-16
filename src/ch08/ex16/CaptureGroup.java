package ch08.ex16;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * city, state, zipを含む行を解析する。
 * 
 * @author 山田 晃一
 */
public class CaptureGroup {
    /**
     * エントリポイント
     *
     * @param args
     *            引数（未使用）
     */
    public static void main(final String[] args) {
        zip("frankfort, KY, 40601-8929");
        System.out.println();
        zip("frankfort,KY,   40601");
    }

    /**
     * コンパイル済みパターンの定義
     * <li>cityは最初のカンマまで。</li>
     * <li>stateはカンマの後の空白以外の文字から2文字分。直後にカンマが必要。</li>
     * <li>zipは直前のカンマの後の空白以外の文字から５、もしくは９文字分。直後に余計な文字は認めない。</li>
     */
    final static String REGEX = "(?<city>[\\p{L} ]+),\\s*(?<state>[A-Z]{2}),\\s*(?<zip>\\d{5}(-\\d{4})?)";
    final static Pattern PATTERN = Pattern.compile(REGEX);

    /**
     * パターンに一致するものの各グループを表示する。
     *
     * @param line
     *            解析する文字列
     */
    public static void zip(final String line) {
        final Matcher matcher = PATTERN.matcher(line);
        if (matcher.matches()) {
            System.out.println("city  : " + matcher.group("city"));
            System.out.println("state : " + matcher.group("state"));
            System.out.println("zip   : " + matcher.group("zip"));
        }
    }
}
