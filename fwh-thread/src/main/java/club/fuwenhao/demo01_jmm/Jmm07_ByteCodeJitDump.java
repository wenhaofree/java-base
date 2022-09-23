package club.fuwenhao.demo01_jmm;

/**
 * @program: fwh-parent
 * @description: 编译字节码-查看dump等信息--需要IDE配置-添加相关插件
 * @author: fwh
 * @date: 2021-06-10 10:56
 **/
public class Jmm07_ByteCodeJitDump {
    private static int c = 1;

    public static int refresh() {
        int a = 0;
        int b = 1;
        int sub = a + b + c;


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });


        return sub;
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread0 = new Thread(() -> {
            System.out.println(String.format("sub0:%d", refresh()));
        });

        thread0.start();

        Thread thread1 = new Thread(() -> {
            System.out.println(String.format("sub1:%d", refresh()));
        });

        thread1.start();
    }
}
