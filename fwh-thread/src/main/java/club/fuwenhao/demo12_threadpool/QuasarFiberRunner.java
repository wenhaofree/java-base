package club.fuwenhao.demo12_threadpool;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.fibers.Suspendable;
import co.paralleluniverse.strands.Strand;
import co.paralleluniverse.strands.SuspendableRunnable;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ：杨过
 * @date ：Created in 2020/8/24
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description: 纤程
 * vmargs : -javaagent:C:\Users\yangguo\.m2\repository\co\paralleluniverse\quasar-core\0.7.6\quasar-core-0.7.6.jar
 **/
@Slf4j
public class QuasarFiberRunner {
    /**
     * 纤程概念 -纯Java是不支持的，需要引用三方包-quasar
     * @throws InterruptedException
     * @throws SuspendExecution
     */
    @Suspendable
    static void m1() throws InterruptedException, SuspendExecution {
        String m = "m1";
        //log.info("m1 begin");
        m = m2();
        //log.info("m1 end");
    }

    static String m2() throws SuspendExecution, InterruptedException {
        String m = m3();
        Strand.sleep(1000);
        return m;
    }

    @Suspendable
    static String m3() {
        List l = Stream.of(1,2,3).filter(i -> i%2 == 0).collect(Collectors.toList());
        return l.toString();
    }
    static public void main(String[] args) throws ExecutionException, InterruptedException {
        int count = 10000;
        testThreadpool(count);
        testFiber(count);
    }
    static void testThreadpool(int count) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(count);
        ExecutorService es = Executors.newFixedThreadPool(200);
        LongAdder latency = new LongAdder();
        long t = System.currentTimeMillis();
        for (int i =0; i< count; i++) {
            es.submit(() -> {
                long start = System.currentTimeMillis();
                try {
                    m1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (SuspendExecution suspendExecution) {
                    suspendExecution.printStackTrace();
                }
                start = System.currentTimeMillis() - start;
                latency.add(start);
                latch.countDown();
            });
        }
        latch.await();
        t = System.currentTimeMillis() - t;
        long l = latency.longValue() / count;
        System.out.println("thread pool took: " + t + ", latency: " + l + " ms");
        es.shutdownNow();
    }

    static void testFiber(int count) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(count);
        LongAdder latency = new LongAdder();
        long t = System.currentTimeMillis();
        for (int i =0; i< count; i++) {
            new Fiber<Void>("Caller", new SuspendableRunnable() {
                @Override
                public void run() throws SuspendExecution, InterruptedException {
                    long start = System.currentTimeMillis();
                    m1();
                    start = System.currentTimeMillis() - start;
                    latency.add(start);
                    latch.countDown();
                }
            }).start();
        }
        latch.await();
        t = System.currentTimeMillis() - t;
        long l = latency.longValue() / count;
        System.out.println("fiber took: " + t  + ", latency: " + l + " ms");
    }
}
