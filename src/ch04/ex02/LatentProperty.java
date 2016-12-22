package ch04.ex02;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 初めて取得されたときに生成されるプロパティ
 *
 * @author 山田晃一
 */
public class LatentProperty {
    /**
     * プロパティが生成されるまでの間使う変数
     */
    private String text;
    /**
     * 遅延生成されるプロパティ
     */
    private StringProperty textProperty;

    /**
     * このメソッド呼び出しの時、プロパティが生成されていなければ生成する。
     *
     * @return プロパティ
     */
    public final StringProperty textProperty() {
        if (textProperty == null) {
            textProperty = new SimpleStringProperty();
        }
        return textProperty;
    }

    /**
     * 新しい値を設定する
     *
     * @param newValue
     *            新しい値
     */
    public final void setText(final String newValue) {
        if (textProperty == null) {
            text = newValue;
        } else {
            textProperty.set(newValue);
        }
    }

    /**
     * 値を取得する
     *
     * @return 値
     */
    public final String getText() {
        return textProperty == null ? text : textProperty.get();
    }
}
