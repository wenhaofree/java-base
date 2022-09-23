package club.fuwenhao.demo01_jmm;

/**
 * @program: fwh-parent
 * @description: 并发：多线程标识，使用关键字volatile使其标识为可见性
 * @author: fwh
 * @date: 2021-06-08 10:39
 **/
public class Jmm03_CodeVisibility {

    private static boolean initFlag = false;
//    private volatile static boolean initFlag = false;

    private volatile static int counter = 0;

    public static void refresh() {
        System.out.println(("refresh data......."));
        initFlag = true;
        System.out.println(("refresh data success......."));
    }

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            while (!initFlag) {
//                System.out.println("runing");
//                counter++;
            }
//            System.out.println(counter);
            System.out.println(("线程：" + Thread.currentThread().getName()
                    + "当前线程嗅探到initFlag的状态的改变"));
        }, "threadA");
        threadA.start();


        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //线程B刷新方法实现更改状态
        Thread threadB = new Thread(() -> {
            refresh();
        }, "threadB");
        threadB.start();
    }
}
