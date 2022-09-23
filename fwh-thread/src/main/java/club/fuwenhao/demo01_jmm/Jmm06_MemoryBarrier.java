package club.fuwenhao.demo01_jmm;

/**
 * @program: fwh-parent
 * @description: volatile的内存屏障
 *
 * 第一次：普通读写、第二次普通读写   导致指令重排序。
 * volatile的读写可以防止 指令重排序。具体可以参见云笔记内容
 *
 * @author: fwh
 * @date: 2021-06-10 10:55
 **/
public class Jmm06_MemoryBarrier {
    int a=0;
    int c=0;
    public volatile int m1 = 1;
    public volatile int m2 = 2;

    public void readAndWrite() {
        int i = m1;   // 第一个volatile读
        a = 4;
        int j = m2;   // 第二个volatile读

        int c = 2; //普通写
        m1 = 3;
        a = i + j;    // 普通写

        m1 = i + 1;   // 第一个volatile写
        m2 = j * 2;   // 第二个 volatile写
    }
}
