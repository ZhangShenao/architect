package william.jvm.tools;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author zhangshenao
 * @Date 2020-07-05
 * @Description 使用jstack命令可以检测到线程死锁
 */
public class TestDeadLock {
    private static final Lock L1 = new ReentrantLock();

    private static final Lock L2 = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                L1.lock();
                System.err.println(Thread.currentThread().getName() + " Get Lock L1");
                Thread.sleep(5000L);
                L2.lock();
                System.err.println(Thread.currentThread().getName() + " Get Lock L2");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                L2.unlock();
                L1.unlock();
            }
        }).start();

        new Thread(() -> {
            try {
                L2.lock();
                System.err.println(Thread.currentThread().getName() + " Get Lock L2");
                Thread.sleep(5000L);
                L1.lock();
                System.err.println(Thread.currentThread().getName() + " Get Lock L1");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                L1.unlock();
                L2.unlock();
            }
        }).start();
    }
}
