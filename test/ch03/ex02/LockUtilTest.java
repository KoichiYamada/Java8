package ch03.ex02;

import static org.junit.Assert.*;

import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

/**
 * LockUtilのテストクラス
 *
 * @author 山田晃一
 */
public class LockUtilTest {

	@Test
	public void testWithLock() {
		final ReentrantLock lockObj = new ReentrantLock();

		assertFalse(lockObj.isLocked());

		LockUtil.withLock(lockObj, () -> {
			assertTrue(lockObj.isLocked());
		});

		assertFalse(lockObj.isLocked());
	}
}
