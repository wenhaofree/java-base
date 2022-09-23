package club.fuwenhao;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fwh
 * @email fuwenhao594@163.com
 * @date 2021/1/24 5:37 下午
 */
public class ReentrantLockDemo2 {
    /**
     * ReentrantLock中lockInterruptibly()和lock()最大的区别就是中断相应问题：
     * <p>
     * lock()是支持中断相应的阻塞试的获取方式，因此即使主动中断了锁的持有者，但是它不能立即unlock(),仍然要机械版执行完所有操作才会释放锁。
     * <p>
     * lockInterruptibly()是 优先响应中断的，这样有个优势就是可以通过tryLock()、tryLock(timeout, TimeUnit.SECONDS)方法，中断优先级低的Task，及时释放资源给优先级更高的Task，甚至看到网上有人说可以做防止死锁的优化；
     * <a href="https://blog.csdn.net/qiang_zi_/article/details/104461378?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-2.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-2.control">网址</a>
     *
     * @param args
     * @return void
     * @author fwh [2021/1/24 && 5:39 下午]
     */
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        try {
            lock.lockInterruptibly();
            long timeout = 10000L;
            if (lock.tryLock(timeout, TimeUnit.SECONDS)) {
                //TODO
                System.out.println("具体事情");
            } else {
                //超时直接中断优先级低的Task
                Thread.currentThread().interrupt();
                lock.lock();
                //TODO
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

        }
    }
}
