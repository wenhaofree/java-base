import club.fuwenhao.bean.UserInfo;
import org.junit.Test;

import java.util.*;

/**
 * 汇集set相关问题
 *
 * @author fwh
 * @email fuwenhao594@163.com
 * @date 2021/1/21 7:31 下午
 */
public class SetTest {

    /**
     * set
     * 特点：无序不重复
     * <p>
     * HashSet、LinkedHashSet、TreeSet区别：  网址：https://juejin.cn/post/6844903904107298830#heading-12
     * 相同点：
     * 1)HashSet、LinkedHashSet、TreeSet都实现了Set接口
     * 2)三者都保证了元素的唯一性，即不允许元素重复
     * 3)三者都不是线程安全的
     * 可以使用Collections.synchronizedSet()方法来保证线程安全
     * 不同点：
     * 4.2.1 排序
     * HashSet不保证元素的顺序
     * LinkHashSet保证FIFO即按插入顺序排序
     * TreeSet保证元素的顺序，支持自定义排序规则
     * 4.2.2 null值
     * HashSet，LinkedHashSet允许添加null值，TreeSet不允许添加null值，添加null时会抛出java.lang.NullPointerException异常。
     * 4.2.3 性能
     * 理论情况下，添加相同数量的元素， HashSet最快，其次是LinkedHashSet，TreeSet最慢（因为内部要排序）。
     * <p>
     * TreeSet的两种排序方式(面试常问)
     * 5.1 自然排序
     * 自然排序的实现方式是让Student类实现接口Comparable，并重写该接口的方法compareTo，该方法会定义排序规则。
     * 5.2 比较器排序
     * 比较器排序的实现方式是新建一个比较器类，实现接口Comparator，重写接口中的Compare()方法。
     *
     * @param
     * @return void
     * @author fwh [2021/1/12 && 10:20 下午]
     */
    @Test
    public void set() {
        Set set = new HashSet();//是无序的
//        Set set = new TreeSet();//是有序的
//        Set set = new LinkedHashSet();//保持添加的顺序
        set.add(2);
        set.add(1);
        set.add(3);
        set.add(1);
        for (Object o : set) {
            System.out.println(o);
        }
    }

    /**
     * set内部原理存储比较-排除重复对象
     * <a href="https://zhuanlan.zhihu.com/p/136342552">HashSet是如何保证元素不重复的</a>
     *
     * @description 内部原理也是hashmap，通过唯一的key来判断是否重复。一、先hash计算位置是否有值。二、有值的话通过hash和equals比较是否重复
     * @return void
     * @author fwh [2021/3/24 && 9:04 下午]
     */
    @Test
    public void setObject() {
        Set<UserInfo> userInfoSet = new HashSet<>();
        final UserInfo userInfo1 = new UserInfo();
        final UserInfo userInfo2 = new UserInfo();
        userInfo1.setName("张三");
        userInfo2.setName("张三");
        userInfoSet.add(userInfo1);
        userInfoSet.add(userInfo2);
        for (UserInfo userInfo : userInfoSet) {
            System.out.println(userInfo);
        }
    }
    /**
     * list转换成set
     * @param
    * @return void
    * @author fuwenhao
    * @date 2021/7/14 11:22 上午
    */
    @Test
    public void listToSet(){
        List testList = new ArrayList();
        testList.add("1");
        testList.add("2");
        testList.add("3");
        testList.add("1");
        System.out.println(testList);
        HashSet hashSet = new HashSet<>(testList);
        System.out.println(hashSet);

    }
}
