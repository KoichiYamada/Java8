package ch08.ex14;

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * ObjectNonNull の使用方法と、もっと役立つエラーメッセージ。
 * <p>
 * コンストラクタで引数チェックに使い、ラムダでメッセージを構築すれば、nullで無い時にコストがかからない。
 * </p>
 * <p>
 * ということはテキストにも書いてあるはずだけど、何を求められている？（もっと役立つエラーメッセージとは？）
 * </p>
 *
 * @author 山田 晃一
 */
public class Appoint {
    private final String title;
    private final ZonedDateTime zonedDateTime;

    public Appoint(final String title, final ZonedDateTime zonedDateTime) {
        this.title = Objects.requireNonNull(title, () -> "title must not be null.");
        this.zonedDateTime = Objects.requireNonNull(zonedDateTime,
                () -> "zonedDateTime must not be null.");
    }
}
