package ch02.ex10;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * DoubleStreamsのテストクラス
 *
 * @author 山田晃一
 */
public class DoubleStreamsTest {
	@Test
	public void testAverage() {
		final double[] doubles = new double[] { 1.2, 3.4, 5.6, 7.8 };
		final DoubleStream doubleStream = Arrays.stream(doubles); // 後でaverage使うので↓
		final Stream<Double> objStream = Arrays.stream(doubles).boxed(); // ここでは別のstreamにする
		final double ave = DoubleStreams.average(objStream);
		assertEquals(doubleStream.average().getAsDouble(), ave, 0.0);
	}
}
