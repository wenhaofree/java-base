package club.fuwenhao.demo09_unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author ：图灵-杨过
 * @date：2019/7/14
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :
 */
public class UnsafeInstance {
    /**
     * Unsafe是位于sun.misc包下的一个类，主要提供一些用于执行低级别、不安全操作的 方法，如直接访问系统内存资源、自主管理内存资源等，这些方法在提升Java运行效率、增
     * 强Java语言底层资源操作能力方面起到了很大的作用。但由于Unsafe类使Java语言拥有了 类似C语言指针一样操作内存空间的能力，这无疑也增加了程序发生相关指针问题的风险。 在程序中过度、不正确使用Unsafe类会使得程序出错的概率变大，使得Java这种安全的语 言变得不再“安全”，因此对Unsafe的使用一定要慎重。
     * @return
     */
    public static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        int j=1;
        reflectGetUnsafe().loadFence();
        int i= 0;
    }
}
