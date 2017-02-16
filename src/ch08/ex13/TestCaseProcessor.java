package ch08.ex13;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

/**
 * javac -cp src -processorpath src -processor ch08.ex13.TestCaseProcessor
 * src/ch08/ex13/TestCaseDemo.java
 *
 * @author 山田 晃一
 */

@SupportedAnnotationTypes({ "ch08.ex13.TestCase", "ch08.ex13.TestCases" })
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class TestCaseProcessor extends AbstractProcessor {

	@Override
	public boolean process(final Set<? extends TypeElement> annotations,
			final RoundEnvironment roundEnv) {
		for (final TypeElement t : annotations) {
			final Filer filer = processingEnv.getFiler();
			try (Writer writer = filer.createSourceFile("ch08.ex13.Tester").openWriter()) {
				writer.write("package ch08.ex13;" + System.lineSeparator());
				writer.write(System.lineSeparator());
				writer.write("public class Tester {" + System.lineSeparator());
				writer.write("\tpublic static void main(final String[] args) {"
						+ System.lineSeparator());
				for (final Element e : roundEnv.getElementsAnnotatedWith(t)) {
					final String methodName = e.getEnclosingElement() + "." + e.getSimpleName();
					final TestCase[] testCases = e.getAnnotationsByType(TestCase.class);
					for (final TestCase testCase : testCases) {
						writer.write("\t\t{" + System.lineSeparator());
						writer.write("\t\t\tSystem.out.print(\"testing " + e.getSimpleName()
								+ " with param " + testCase.params() + "\");"
								+ System.lineSeparator());
						writer.write("\t\t\tfinal int ret = " + methodName + "(" + testCase.params()
								+ ");" + System.lineSeparator());
						writer.write("\t\t\tif (ret == " + testCase.expected() + ") {"
								+ System.lineSeparator());
						writer.write("\t\t\t\tSystem.out.println(\" : SUCCESS\");"
								+ System.lineSeparator());
						writer.write("\t\t\t} else {" + System.lineSeparator());
						writer.write("\t\t\t\tSystem.out.println(\" : FAILURE : expected "
								+ testCase.expected() + " but actually \" + ret);"
								+ System.lineSeparator());
						writer.write("\t\t\t}" + System.lineSeparator());
						writer.write("\t\t}" + System.lineSeparator());
					}
					writer.write("\t\tSystem.out.println(\"end\");" + System.lineSeparator());
				}
				writer.write("\t}" + System.lineSeparator());
				writer.write("}" + System.lineSeparator());
			} catch (final IOException e1) {
				e1.printStackTrace();
			}
		}
		return true;
	}
}
