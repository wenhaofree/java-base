package club.fuwenhao;

/**
 * @author fwh
 * @email fuwenhao594@163.com
 * @date 2021/1/24 5:03 下午
 */
public class DeadLock {
    /**
     * 死锁demo
     * <a href="https://www.runoob.com/java/thread-deadlock.html">死锁样例以及解决方案</a>
     *
     * @return
     * @author fwh [2021/1/24 && 5:03 下午]
     */
    public static void main(String[] args) {
        MyThread my = new MyThread();
        new Thread(my, "Thread-a").start();
        new Thread(my, "Thread-b").start();
    }
}

class MyThread implements Runnable {
    private Object o1 = new Object();
    private Object o2 = new Object();
    private boolean flag = true;

    @Override
    public void run() {
        if (flag) {
            flag = false;
            synchronized (o1) {
                System.out.println(Thread.currentThread().getName() + "---have o1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName() + "---have o2");
                }
            }
        } else {
            flag = true;
            synchronized (o2) {
                System.out.println(Thread.currentThread().getName() + "---have o2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + "---have o1");
                }
            }
        }
    }
}
