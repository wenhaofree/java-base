package club.fuwenhao.controller;

/**
 * @author fwh
 * @email fuwenhao594@163.com
 * @date 2021/1/18 3:27 下午
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("MyRunnable线程名：" + Thread.currentThread().getName());
    }
}
