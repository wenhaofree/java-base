import org.junit.Test;

import java.util.*;

/**
 * 汇集List所有相关问题
 *
 * @author fwh
 * @email fuwenhao594@163.com
 * @date 2021/1/21 7:30 下午
 */
public class ListTest {

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
        List<Integer> list = new ArrayList(1);
        list.add(1);
        list.add(2);
        //三种遍历
        final Iterator<Integer> iterator = list.iterator();
//        if (iterator.hasNext()) {
        while (iterator.hasNext()) {
            final Integer next = iterator.next();
            System.out.println(next);
        }
        list.forEach((index) -> System.out.println(index));
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }

    /**
     * linkedList
     * 底层原理：
     * 优缺点：
     *
     * @param
     * @return void
     * @author fwh [2021/1/21 && 7:45 下午]
     */
    @Test
    public void linkedList() {
        List<String> list = new LinkedList();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(list.toArray());
        list.remove(0);
        list.remove("b");
        System.out.println(list.toArray());
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
     * 判断list是否实现RandomAccess接口来实行indexedBinarySerach(list,key)或iteratorBinarySerach(list,key)方法。ps（instanceof其作用是用来判断某对象是否为某个类或接口类型）
     * 从上面数据可以看出，ArrayList用for循环遍历比iterator迭代器遍历快，LinkedList用iterator迭代器遍历比for循环遍历快，
     * 总结：RandomAccess接口这个空架子的存在，是为了能够更好地判断集合是否ArrayList或者LinkedList，从而能够更好选择更优的遍历方式，提高性能！
     *
     * <a href="https://blog.csdn.net/weixin_39148512/article/details/79234817">参考网址</a>
     *
     * @param
     * @return void
     * @author fwh [2021/1/21 && 7:49 下午]
     */
    @Test
    public void randomAccess() {
    }
}
