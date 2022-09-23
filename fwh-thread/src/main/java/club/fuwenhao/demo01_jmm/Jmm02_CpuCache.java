package club.fuwenhao.demo01_jmm;

/**
 * @program: fwh-parent
 * @description: CPU：局部性原理性能比较
 * @author: fwh
 * @date: 2021-06-08 10:52
 **/
public class Jmm02_CpuCache {
    private static final int RUNS = 10;
    private static final int DIMENSION_1 = 1024 * 1024;
    private static final int DIMENSION_2 = 6;

    private static long[][] longs;

    public static void main(String[] args) throws Exception {
        /*
         * 初始化数组-DIMENSION_1行；DIMENSION_2列
         */
        longs = new long[DIMENSION_1][];
        for (int i = 0; i < DIMENSION_1; i++) {
            longs[i] = new long[DIMENSION_2];
            for (int j = 0; j < DIMENSION_2; j++) {
                longs[i][j] = 1L;
            }
        }
        System.out.println("初始化完毕....");

        //横向遍历行数据-遍历所有数据；
        //原理：局部性原理-已经获取位置的数据，在一段时间内会再拿获取附近位置的数据。
        long sum = 0L;
        long start = System.currentTimeMillis();
        for (int r = 0; r < RUNS; r++) {
            for (int i = 0; i < DIMENSION_1; i++) {//DIMENSION_1=1024*1024
                for (int j=0;j<DIMENSION_2;j++){//6
                    sum+=longs[i][j];
                }
            }
        }
        System.out.println("spend time1:"+(System.currentTimeMillis()-start));
        System.out.println("sum1:"+sum);


        //纵向遍历列数据-遍历所有数据
        //列的数据遍历不是局部性原理-挨个遍历数据-性能效率较低
        sum = 0L;
        start = System.currentTimeMillis();
        for (int r = 0; r < RUNS; r++) {
            for (int j=0;j<DIMENSION_2;j++) {//6
                for (int i = 0; i < DIMENSION_1; i++){//1024*1024
                    sum+=longs[i][j];
                }
            }
        }
        System.out.println("spend time2:"+(System.currentTimeMillis()-start));
        System.out.println("sum2:"+sum);

    }
}
