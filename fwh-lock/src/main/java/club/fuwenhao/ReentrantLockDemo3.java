package club.fuwenhao;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author fwh
 * @email fuwenhao594@163.com
 * @date 2021/1/24 5:37 下午
 */
public class ReentrantLockDemo3 {
    /**
     * 读写锁
     *
     * @param args
     * @return void
     * @author fwh [2021/1/24 && 5:47 下午]
     */
    public static void main(String[] args) {
        final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        final ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        final ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        writeLock.lock();
        readLock.lock();
    }
}
