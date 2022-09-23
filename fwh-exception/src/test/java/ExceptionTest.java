import club.fuwenhao.exception.MyException;
import org.junit.Test;

/**
 * @author fwh
 * @email fuwenhao594@163.com
 * @date 2021/1/18 11:20 上午
 */
public class ExceptionTest {
    /**
     * 自定义异常
     * {@link MyException}
     *
     * @param
     * @return void
     * @author fwh [2021/1/18 && 3:05 下午]
     */
    @Test
    public void myException() throws MyException {
        try {
            int s = 1 / 0;
        } catch (Exception e) {
            throw new MyException("自定义异常");
        }

    }
}
