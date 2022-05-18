package william.concurrent.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/18 下午2:58
 * <p>
 * 基于Semaphore实现限流器
 */
public class RateLimiter {
    public static void main(String[] args) throws InterruptedException {
        Semaphore sema = new Semaphore(2);

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    //基于Semaphore实现限流,将QPS限制在2
                    sema.acquire();
                    System.out.println(Thread.currentThread().getName() + ": exec task...");
                    Thread.sleep(1000L);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    sema.release();
                }
            }, "Thread-" + i).start();
        }

        Thread.sleep(Long.MAX_VALUE);
    }
}
