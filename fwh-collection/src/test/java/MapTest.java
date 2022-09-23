import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 汇集所有Map的问题
 *
 * @author fwh
 * @email fuwenhao594@163.com
 * @date 2021/1/21 7:33 下午
 */
public class MapTest {

    /**
     * 深入理解 Map，HashMap，LinkedHashMap，TreeMap区别： https://juejin.cn/post/6844904069648089102
     * HashMap： 网址：https://tracylihui.github.io/2015/07/01/Java%E9%9B%86%E5%90%88%E5%AD%A6%E4%B9%A01%EF%BC%9AHashMap%E7%9A%84%E5%AE%9E%E7%8E%B0%E5%8E%9F%E7%90%86/
     * <p>
     * HashMap的底层原理实现-JDK1.7和JDK1.8版本下的区别-todo-fwh-参见云笔记
     * 底层哈希表的 size 的大小总是 2 的倍数，这是 HashMap 在效率上的一个优化：
     * 当底层数组的 length 为 2^n 时， h & (length - 1) 就相当于对 length 取模，其效率要比直接取模高得多。
     * <p>
     * LinkedHashMap：
     * 拥有 HashMap 的所有特性，它比 HashMap 多维护了一个双向链表，因此可以按照插入的顺序从头部或者从尾部迭代，是有序的，
     * <p>
     * SortedMap：
     * 是一个有序的 Map 接口，按照自然排序，也可以按照传入的 comparator 进行排序，
     * <p>
     * TreeMap：
     * 的底层就是一颗红黑树，它的 containsKey , get , put and remove 方法的时间复杂度是 log(n) ，
     * 并且它是按照 key 的自然顺序（或者指定排序）排列，与 LinkedHashMap 不同，
     * LinkedHashMap 保证了元素是按照插入的顺序排列。
     *
     * @param
     * @return void
     * @author fwh [2021/1/11 && 5:44 下午]
     */
    @Test
    public void hashMap() {
        System.out.println("*************************HashMap*************");
        Map<Integer, String> map1 = new HashMap<Integer, String>();
        map1.put(1, "apple");
        map1.put(2, "banana");
        map1.put(3, "pear");
        for (Iterator it = map1.keySet().iterator(); it.hasNext(); ) {
            Object key = it.next();
            System.out.println(key + "=" + map1.get(key));
        }
        //HashMap
        System.out.println("------HashMap无序输出------");
        HashMap hsMap = new HashMap();
        hsMap.put("3", "Value3");
        hsMap.put("1", "Value1");
        hsMap.put("2", "Value2");
        hsMap.put("b", "ValueB");
        hsMap.put("a", "ValueA");
        Iterator it = hsMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            System.out.println("Key: " + e.getKey() + "--Value: " + e.getValue());
        }
    }

    /**
     * ConcurrentHashMap   https://juejin.cn/post/6844903597373669389#heading-4
     * <p>
     * ConcurrentHashMap 是线程安全的 HashMap 的实现。
     * (1) ConcurrentHashMap对整个桶数组进行了分割分段(Segment)，然后在每一个分段上都用
     * lock 锁进行保护，相对于 HashTable 的 syn 关键字锁的粒度更精细了一些，并发性能更
     * 好，而 HashMap 没有锁机制，不是线程安全的。
     * (2) HashMap 的键值对允许有 null，但是 ConCurrentHashMap 都不允许。
     *
     * @param
     * @return void
     * @author fwh [2021/1/12 && 10:55 下午]
     */
    @Test
    public void concurrentHashMap() {
        Map<Integer, String> map = new ConcurrentHashMap();
        map.put(1, "1");
        System.out.println(map);
    }

    /**
     * treeMap
     *
     * @param
     * @return void
     * @author fwh [2021/1/11 && 5:50 下午]
     */
    @Test
    public void treeMap() {
        System.out.println("------TreeMap按Key排序输出------");
        TreeMap teMap = new TreeMap();
        teMap.put("3", "Value3");
        teMap.put("1", "Value1");
        teMap.put("2", "Value2");
        teMap.put("b", "ValueB");
        teMap.put("a", "ValueA");
        Iterator tit = teMap.entrySet().iterator();
        while (tit.hasNext()) {
            Map.Entry e = (Map.Entry) tit.next();
            System.out.println("Key: " + e.getKey() + "--Value: " + e.getValue());
        }
    }

    /**
     * linkedHashMap
     *
     * @param
     * @return void
     * @author fwh [2021/1/11 && 5:42 下午]
     */
    @Test
    public void linkedHashMapTest() {
        System.out.println("*************************LinkedHashMap*************");
        Map<Integer, String> map = new LinkedHashMap<Integer, String>();
        map.put(1, "apple");
        map.put(2, "banana");
        map.put(3, "pear");
        final Iterator<Integer> iterator = map.keySet().iterator();
        for (Iterator it = iterator; it.hasNext(); ) {
            Object key = it.next();
            System.out.println(key + "=" + map.get(key));
        }
        map.forEach((key, value) -> {
            System.out.println(key + "=" + value);
        });

        //LinkedHashMap
        System.out.println("--LinkedHashMap根据输入的顺序输出--");
        LinkedHashMap lhsMap = new LinkedHashMap();
        lhsMap.put("3", "Value3");
        lhsMap.put("1", "Value1");
        lhsMap.put("2", "Value2");
        lhsMap.put("b", "ValueB");
        lhsMap.put("a", "ValueA");
        Iterator lit = lhsMap.entrySet().iterator();
        while (lit.hasNext()) {
            Map.Entry e = (Map.Entry) lit.next();
            System.out.println("Key: " + e.getKey() + "--Value: " + e.getValue());
        }
    }
}
