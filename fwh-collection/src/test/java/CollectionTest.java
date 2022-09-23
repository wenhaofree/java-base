import org.junit.Test;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author fwh
 * @email fuwenhao594@163.com
 * @date 2021/1/11 5:29 下午
 */
public class CollectionTest {

    /**
     * list
     * 特点：有序且重复
     * <p>
     * List 和 Set 区别：
     * List,Set 都是继承自 Collection 接口
     * List 特点:元素有放入顺序，元素可重复
     * Set 特点:元素无放入顺序，元素不可重复，重复元素会覆盖掉
     * <p>
     * Set 和 List 对比:
     * Set:检索元素效率低下，删除和插入效率高，插入和删除不会引起元素位置改变。
     * List:和数组类似，List 可以动态增长，查找元素效率高，插入删除元素效率低，因为会引起 其他元素位置改变。
     *
     * @param
     * @return void
     * @author fwh [2021/1/11 && 5:59 下午]
     */
    @Test
    public void list() {
        List<String> listWithDup = new ArrayList<String>();
        listWithDup.add("1");
        listWithDup.add("2");
        listWithDup.add("3");
        listWithDup.add("1");
        List<String> listWithoutDup = new ArrayList<String>(new HashSet<String>(listWithDup));
        List<String> lists = new ArrayList<String>(new HashSet<String>(listWithDup));
        System.out.println("list with dup:" + listWithDup);
        System.out.println("list without dup:" + listWithoutDup);
        List list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("a");
        list.add("b");
        list.add("2");
        list.add("b");
        List list1 = removeDuplicate(list);
        for (int i = 0; i < list1.size(); i++) {
            System.out.println(list1.get(i));
        }
    }

    //去重
    private static List removeDuplicate(List list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            Object element = iter.next();
            if (set.add(element)) {
                newList.add(element);
            }
        }
        return newList;
    }

    /**
     * ArrayList和LinkedList的区别？  https://juejin.cn/post/6844903597776306183
     * <p>
     * 1.ArrayList是实现了基于动态数组的数据结构，LinkedList基于链表的数据结构。
     * 2.对于随机访问get和set，ArrayList觉得优于LinkedList，因为LinkedList要移动指针。
     * 3.对于新增和删除操作add和remove，LinedList比较占优势，因为ArrayList要移动数据。
     *
     * @param
     * @return void
     * @author fwh [2021/1/12 && 10:35 下午]
     */
    @Test
    public void arrayList() {
//        List list = new ArrayList(1);
        List list = new LinkedList();
        list.add(1);
        list.add(2);
        System.out.println(list);
    }

    /**
     * CopyOnWriteArrayList-- 多线程安全问题-解决fail-fast问题
     *
     * @return {@link ListConcurrentTest}
     * @author fwh [2021/1/18 && 10:26 上午]
     * @see <a href="https://blog.csdn.net/hua631150873/article/details/51306021"/>
     */
    public void copyOnWriteArrayListTest() {
    }

    /**
     * array和list相互转换
     *
     * @param
     * @return void
     * @author fwh [2021/1/18 && 10:52 上午]
     */
    @Test
    public void arrayToList() {
        List list = new ArrayList();
        list.add("123");
        list.add("456");
        System.out.println(list.toArray());
        //array to list
        String[] array = new String[]{"123", "456"};
        System.out.println(Arrays.asList(array));
    }

    /**
     * 多线程情况下-安全集合采用synchronizedList
     *
     * @param
     * @return void
     * @author fwh [2021/1/18 && 10:57 上午]
     */
    @Test
    public void synchronizedList() {
        List<String> list = new ArrayList<>();
        List<String> synchronizedList = Collections.synchronizedList(list);
        synchronizedList.add("aaa");
        synchronizedList.add("bbb");
        for (int i = 0; i < synchronizedList.size(); i++) {
            System.out.println(synchronizedList.get(i));
        }
    }


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
//        Set set = new HashSet();//是无序的
//        Set set = new TreeSet();//是有序的
        Set set = new LinkedHashSet();//保持添加的顺序
        set.add(2);
        set.add(1);
        set.add(3);
        set.add(1);
        for (Object o : set) {
            System.out.println(o);
        }
    }

    /**
     * Set与List之间转化：
     * List list = new ArrayList(set);
     * Set set = new HashSet(list);
     * 但是有一点,转换当中可能要丢失数据,尤其是从list转换到set的时候,因为set不能有重复数据
     * https://blog.csdn.net/mhs624014469/article/details/78029243
     */
    @Test
    public void listToSet(){
        List<String> stringList = new ArrayList<>();
        stringList.add("zhangsan02");
        stringList.add("zhangsan01");
        stringList.add("zhangsan03");
        stringList.add("zhangsan01");
        System.out.println(stringList);
        Set<String> set = new HashSet<>(stringList);
        System.out.println("set:"+set);
    }


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

    /**
     * Iterator-移除边遍历边移除
     * list.remove()会报错异常
     *
     * @param
     * @return void
     * @author fwh [2021/1/18 && 10:46 上午]
     */
    @Test
    public void iterator() {
        List<String> list = new ArrayList<>();
        list.add("zhangsan");
        list.add("lisi");
        list.add("wangwu");
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String obj = (String) it.next();
            System.out.println(obj);
            System.out.println(list.size());
            it.remove();
        }
    }

    /**
     * 阻塞队列
     * 移除的区别：如果没有元素 poll()会返回 null，而 remove()会直接抛出 NoSuchElementException 异常。
     *
     * @param
     * @return void
     * @author fwh [2021/1/18 && 11:06 上午]
     */
    @Test
    public void blockingQueue() {
        Queue queue = new LinkedList();
        queue.offer("zhangsan");
        queue.offer("lisi");
        //add
        System.out.println(queue.size());
        System.out.println(queue.poll());
        System.out.println(queue.remove());
    }


    /**
     * 弱引用
     *
     * @param
     * @return void
     * @author fwh [2021/1/11 && 5:59 下午]
     */
    @Test
    public void softReference() {
        //弱引用.
        SoftReference<String> sr = new SoftReference<String>(new String("hello"));
        System.out.println("sr:" + sr.get());
        System.gc();
        System.out.println("sr2:" + sr.get());
    }

    /**
     * 软引用
     *
     * @param
     * @return void
     * @author fwh [2021/1/11 && 6:01 下午]
     */
    @Test
    public void weakReference() {
        //软引用
        WeakReference<String> wr = new WeakReference<String>(new String("hello"));
        System.out.println("wr:" + wr.get());
        System.gc();
        System.out.println("wr2:" + wr.get());
    }

    /**
     * 栈堆类-先进后出
     *
     * @param
     * @return void
     * @author fwh [2021/1/18 && 10:22 上午]
     */
    @Test
    public void statck() {
        Stack<String> strings = new Stack<>();
        strings.add("zhagnsan");
        strings.add("lisi");
        strings.add("wangwu");
        System.out.println(strings.firstElement());
        System.out.println(strings.pop());
        strings.forEach((value) -> System.out.println(value));
    }
}
