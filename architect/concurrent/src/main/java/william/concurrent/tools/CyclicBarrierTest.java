package william.concurrent.tools;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/19 13:48
 * @Description:Usage of CyclicBarrier
 */
public class CyclicBarrierTest {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2,new Task());

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Thread.sleep(Math.abs(ThreadLocalRandom.current().nextInt(4000)));
                cyclicBarrier.await();
                System.err.println("1");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(Math.abs(ThreadLocalRandom.current().nextInt(4000)));
                cyclicBarrier.await();
                System.err.println("2");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }


    private static class Task implements Runnable{
        @Override
        public void run() {
            System.err.println("3");
        }
    }
}
