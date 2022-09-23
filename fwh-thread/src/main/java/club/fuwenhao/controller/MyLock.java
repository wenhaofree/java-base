package club.fuwenhao.controller;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fwh
 * @email fuwenhao594@163.com
 * @date 2021/1/18 3:43 下午
 */
//@Slf4j
public class MyLock {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println("获得锁");
        } catch (Exception e) {
        } finally {
            System.out.println("释放锁");
            lock.unlock();
        }
    }
}