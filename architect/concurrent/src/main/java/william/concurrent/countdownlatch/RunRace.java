package william.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/19 上午8:17
 * <p>
 * 使用CountDownLatch模拟跑步竞赛
 */
public class RunRace {
    public static void main(String[] args) throws InterruptedException {
        int playerNum = 10;
        CountDownLatch latch = new CountDownLatch(10);

        for (int i = 0; i < playerNum; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + ": 开始准备...");
                    Thread.sleep(ThreadLocalRandom.current().nextLong(2000, 5000));
                    System.out.println(Thread.currentThread().getName() + ": 准备就绪!");
                    latch.countDown();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, "Player-" + (i + 1)).start();
        }

        latch.await();

        System.out.println("比赛开始!");
        Thread.sleep(2000L);
    }
}
