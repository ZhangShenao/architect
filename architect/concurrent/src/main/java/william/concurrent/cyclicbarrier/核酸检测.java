package william.concurrent.cyclicbarrier;

import java.util.concurrent.*;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/19 上午9:47
 * <p>
 * 使用CyclicBarrier模拟核算检测过程
 * 10个人为一小组,批量进行核算检测
 * 如果不满10个人,无法组成一组,需要等待
 */
public class 核酸检测 {
    public static void main(String[] args) throws InterruptedException {
        int groupSize = 10;
        int totalCount = 2000;
        ExecutorService executor = Executors.newFixedThreadPool(groupSize);

        CyclicBarrier barrier = new CyclicBarrier(groupSize, new 大白());

        for (int i = 0; i < totalCount; i++) {
            executor.submit(new 社区居民("" + (i + 1), barrier));
        }

        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        System.out.println("全体居民核酸完成");
    }

    private static class 社区居民 implements Runnable {
        private String ID;
        private CyclicBarrier barrier;

        public 社区居民(String ID, CyclicBarrier barrier) {
            this.ID = ID;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                System.out.println("居民-" + ID + ": 准备下楼做核酸...");
                System.out.println("居民-" + ID + "开始排队, 等待凑够10人小组...");
                Thread.sleep(ThreadLocalRandom.current().nextLong(5000L, 10000L));
                barrier.await();

                System.out.println("居民-" + ID + "开始做核酸...");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static class 大白 implements Runnable {

        @Override
        public void run() {
            System.out.println("来张嘴，啊~");
        }
    }
}
