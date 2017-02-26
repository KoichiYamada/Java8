package ch09.ex03;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * 複数キャッチする場合、Throwsには何が書かれるか。
 * <p>
 * それぞれを列挙する。この例では共通のスーパータイプであるIOExceptionとすることもできるが、何が起きたかより明確な個別が良いと思う。
 * </p>
 * <p>
 * 可能なら、再スローするより、そのレイヤーで意味のある例外に置き換えるべき。
 * </p>
 *
 * @author 山田晃一
 */
public class ThrowsIsWhat {
	/**
	 * サンプルメソッド
	 */
	public void process() throws FileNotFoundException, UnknownHostException {
		try {
			final Random random = new Random();
			if (random.nextBoolean()) {
				throw new FileNotFoundException();
			} else {
				throw new UnknownHostException();
			}
		} catch (FileNotFoundException | UnknownHostException ex) {
			throw ex;
		}
	}
}
