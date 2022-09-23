package club.fuwenhao.controller;

/**
 * @author fwh
 * @email fuwenhao594@163.com
 * @date 2021/1/18 3:18 下午
 */
public class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println("MyThread线程名："+Thread.currentThread().getName());
    }
}
