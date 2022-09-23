package club.fuwenhao.controller;

import java.util.concurrent.Callable;

/**
 * @author fwh
 * @email fuwenhao594@163.com
 * @date 2021/1/18 3:30 下午
 */
public class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("MyCallable线程名：" + Thread.currentThread().getName());
        return 1;
    }
}
