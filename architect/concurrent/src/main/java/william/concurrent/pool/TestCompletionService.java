package william.concurrent.pool;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author zhangshenao
 * @Date 2019-09-04
 * @Description 使用CompletionService, 结合了线程池和阻塞队列, 对FutureTask进行封装, 先执行完的FutureTask先放入阻塞队列中
 */
public class TestCompletionService {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        CompletionService<Long> completionService = new ExecutorCompletionService<>(executor);
        int taskNum = 10;
        long t = 0L;
        for (int i = 0; i < taskNum; i++) {
            completionService.submit(new Task());
        }
        for (int i = 0; i < taskNum; i++) {
            t += completionService.take().get();
        }
        System.err.println("Total Time: " + t);
        executor.shutdown();
    }

    private static class Task implements Callable<Long> {
        @Override
        public Long call() throws InterruptedException {
            long t = ThreadLocalRandom.current().nextInt(100, 1000);
            Thread.sleep(t);
            return t;
        }
    }
}
