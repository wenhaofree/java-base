package club.fuwenhao.demo01_jmm;

/**
 * 线程模型
 *
 * @author fuwenhao
 * @return
 * @date 2021/6/10 11:01 上午
 */
public class ThreadModel {

    public static void main(String[] args) {

        for (int i = 0; i < 200; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

}
