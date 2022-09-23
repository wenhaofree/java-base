package club.fuwenhao;

import java.util.concurrent.locks.Condition;

/**
 * 应用场景-ReentrantLock.Condition线程通信
 * 经典面试题-两个线程之间交替打印 26英文字母和阿拉伯数字
 *
 * @author fwh
 * @email fuwenhao594@163.com
 * @date 2021/1/24 5:28 下午
 */
public class ReentrantLockDemo {
    public static void main(String[] args) {
        java.util.concurrent.locks.ReentrantLock lock = new java.util.concurrent.locks.ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Thread thread1 = new Thread(() -> {
            try {
                lock.lock();
                //A-Z的26字母对应-65~91
                for (int i = 65; i < 91; i++) {
                    System.out.println("----------thread1------- " + (char) i);
                    condition2.signal();
                    condition1.await();
                }
                condition2.signal();
            } catch (Exception e) {
            } finally {
                lock.unlock();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                lock.lock();
                for (int i = 0; i < 26; i++) {
                    System.out.println("----------thread2------- " + i);
                    condition1.signal();
                    condition2.await();
                }
                condition1.signal();
            } catch (Exception e) {
            } finally {
                lock.unlock();
            }
        });
        thread1.start();
        thread2.start();
    }
}
