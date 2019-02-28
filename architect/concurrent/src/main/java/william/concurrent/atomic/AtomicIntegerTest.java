package william.concurrent.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/21 15:14
 * @Description:Atomic类可以保证单个操作的原子性,但是对于复合操作,可能会存在中间状态,仍然需要加锁保证线程安全
 */
public class AtomicIntegerTest {
    private static int count = 0;

    private static final int THREAD_NUM = 100;
    private static CountDownLatch latch = new CountDownLatch(THREAD_NUM);

    private static final AtomicInteger i = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        /*increment();
        System.err.println(i.get());*/

        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ++count;
                incrementAndGet();
                latch.countDown();
            }).start();
        }
        latch.await();
        System.err.println("count: " + count);  //使用普通int,结果可能不正确
        System.err.println("i: " + i.get());    //使用AtomicInteger,结果正确
    }

    private static void increment() {
        int value = AtomicIntegerTest.i.get();
        while (!(i.compareAndSet(value, value + 1))) {
        }
    }

    private static void incrementAndGet(){
        i.incrementAndGet();
    }
}
