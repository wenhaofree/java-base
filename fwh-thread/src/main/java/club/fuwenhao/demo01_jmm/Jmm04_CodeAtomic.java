package club.fuwenhao.demo01_jmm;

/**
 * @program: fwh-parent
 * @description: 并发：同步锁涉及内部原理
 * volatile不能保证原子性，只是保证内存屏障的有序性
 * @author: fwh
 * @date: 2021-06-08 11:03
 **/
public class Jmm04_CodeAtomic {

    private volatile static int counter = 0;
    static Object object = new Object();

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    synchronized (object){
                        counter++;//分三步- 读，自加，写回
                        //读
                    }
                }
            });
            thread.start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(counter);

    }
}
