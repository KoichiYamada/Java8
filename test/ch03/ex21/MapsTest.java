package ch03.ex21;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.junit.Test;

/**
 * Mapsのテスト
 *
 * @author 山田晃一
 */
public class MapsTest {
	@Test
	public void testMapFutureOfTFunctionOfTU() throws InterruptedException, ExecutionException {
		FutureTask<String> s = new FutureTask<>(() -> "5");
		Future<Integer> i = Maps.map(s, Integer::valueOf);
		new Thread(s).start();
		assertEquals(5, i.get().intValue());
	}
}
