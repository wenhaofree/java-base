package club.fuwenhao;

/**
 * 双重校验锁实现对象单例-synchronized
 *
 * @author fwh
 * @email fuwenhao594@163.com
 * @date 2021/1/24 4:36 下午
 */
public class Singleton {
    /**
     * 采用Synchronized关键字实现
     * 手写疏忽点：
     * 1、添加static关键字
     * 2、少些默认构造方法
     * 3、锁里面添加不对，应该是这个类
     * 4、将变量赋值新的类实例
     * 5、最终返回这个类变量
     *
     * @param null
     * @return
     * @author fwh [2021/1/24 && 4:37 下午]
     */
    private volatile static Singleton singleton;

    private Singleton() {
    }

    public Singleton getSingleton() {
        if (null == singleton) {
            synchronized (Singleton.class) {
                if (null == singleton) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
