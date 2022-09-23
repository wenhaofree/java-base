import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 汇集队列所有问题
 *
 * @author fwh
 * @email fuwenhao594@163.com
 * @date 2021/1/21 7:32 下午
 */
public class QueueTest {
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
}
