package william.concurrent.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/3 11:27
 * @Description:使用自定义Semaphore
 */
public class TestSimpleSemaphore {
    private static final int THREAD_NUM = 30;
    private static final int SEMAPHORE_NUM = 10;
    private static SimpleSemaphore semaphore = new SimpleSemaphore(SEMAPHORE_NUM);

    private static ExecutorService pool = Executors.newFixedThreadPool(THREAD_NUM);

    public static void main(String[] args) {
        //总共30个线程,允许最大并发量为10
        for (int i = 0; i < THREAD_NUM; i++) {
            pool.submit(() -> {
                try {
                    semaphore.acquire();
                    System.err.println(Thread.currentThread().getName() + "--> Acquire Semaphore");
                    Thread.sleep(1000L);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
        }
        pool.shutdown();
    }
}
