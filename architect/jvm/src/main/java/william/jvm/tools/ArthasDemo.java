package william.jvm.tools;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author zhangshenao
 * @Date 2020-07-09
 * @Description 使用Alibaba的Arthas工具进行程序诊断
 */
public class ArthasDemo {
    private static final Lock L1 = new ReentrantLock();

    private static final Lock L2 = new ReentrantLock();

    private static final Set<String> POOL = new HashSet<>();

    public static void main(String[] args) {
        ArthasDemo demo = new ArthasDemo();
        demo.cpuHigh();
        demo.deadLock();
        demo.createInstance();
    }

    //模拟CPU过高
    private void cpuHigh() {
        new Thread(() -> {
            while (true) {

            }
        }).start();
    }

    //模拟死锁
    private void deadLock() {
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

    //模拟不断创建对象
    private void createInstance() {
        new Thread(() -> {
            int count = 0;

            while (true) {
                POOL.add(String.valueOf(++count));

                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
