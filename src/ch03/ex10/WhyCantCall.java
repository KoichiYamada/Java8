package ch03.ex10;

/*
 * UnaryOperator<T>のcomposeの戻り型はFunction<V, T>で、Color::grayscaleの戻り型はColorなので、 Function<Color,
 * T>となり、UnaryOperator<Color>のcomposeでは、Function<Color, Color>となる。 Function<Color,
 * Color>はUnaryOperator<Color>にはキャストできないので、使用できない。 これがもしストラクチャル型なら、使用できた。
 */
public class WhyCantCall {
}
