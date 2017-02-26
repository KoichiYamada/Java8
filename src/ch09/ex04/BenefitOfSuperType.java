package ch09.ex04;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * 複数例外キャッチや、共通の例外となるスーパークラスにより恩恵を得られる状況に遭遇したライブラリには何があるか。
 *
 * @author 山田晃一
 */
public class BenefitOfSuperType {
	/*
	 * 少なくとも二つのConfigurationは同じように処理する可能性が高く、別々にコードを書きたくはないだろう。
	 * さらにおそらく、SAXExceptionやIOExceptionまで含めて、解析失敗とすることが多いのでは？なので、複数例外をキャッチ出るのは便利。
	 */
	public void multiCatch() {
		try {
			final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document document = builder.parse("");
		} catch (FactoryConfigurationError | ParserConfigurationException | SAXException
				| IOException ex) {
		}
	}

	/*
	 * リフレクション関連の例外のスーパークラスとしてReflectiveOperationExceptionが定義されたので、 まとめてキャッチできて便利。
	 */
	public void superClassCatch() {
		try {
			final Class<?> klass = Class.forName("foo");
			final Method method = klass.getMethod("bar", String.class);
			method.invoke(null, "baz");
		} catch (ReflectiveOperationException | SecurityException e) {
		}
	}
}
