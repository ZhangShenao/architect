package william.concurrent.tools;

import java.util.concurrent.*;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/19 15:07
 * @Description:
 */
public class SemaphoreTest {
    private static final int THREAD_NUM = 30;
    private static final int SEMAPHORE_NUM = 10;
    private static Semaphore semaphore = new Semaphore(SEMAPHORE_NUM);

    private static ExecutorService pool = Executors.newFixedThreadPool(THREAD_NUM);

    public static void main(String[] args) {
        for (int i = 0;i < THREAD_NUM;i++){
            pool.submit(() -> {
                try {
                    semaphore.acquire();
                    System.err.println(Thread.currentThread().getName() + "--> Acquire Semaphore");
                    Thread.sleep(1000L );
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    semaphore.release();
                }
            });
        }
        pool.shutdown();
    }
}
