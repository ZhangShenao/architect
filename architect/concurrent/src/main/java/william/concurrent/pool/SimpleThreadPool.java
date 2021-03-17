package william.concurrent.pool;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author zhangshenao
 * @Date 2021-03-17
 * @Description 自己实现简单的线程池，模拟生产者-消费者模式的核心工作机制
 */
@Slf4j
public class SimpleThreadPool {
    //通过阻塞队列维护任务
    private final BlockingQueue<Runnable> tasks;

    private final Queue<Worker> workers;

    public SimpleThreadPool(int corePoolSize, int queueSize) {
        if (corePoolSize <= 0 || queueSize <= 0) {
            throw new IllegalArgumentException("size must be positive!");
        }
        workers = new ArrayDeque<>(corePoolSize);
        tasks = new ArrayBlockingQueue<>(queueSize);

        //初始化核心线程并执行
        for (int i = 0; i < corePoolSize; i++) {
            Worker w = new Worker();
            workers.offer(w);
            w.start();
        }
    }

    public void execute(Runnable r) {
        try {
            tasks.put(r);
        } catch (Exception e) {
            log.error("Execute Task Error!", e);
        }
    }

    public void shutdown() {
        workers.forEach(x -> x.run = false);
    }

    //工作线程
    private class Worker extends Thread {
        private volatile boolean run = true;

        @Override
        public void run() {
            while (run) {
                try {
                    Runnable r = tasks.take();
                    r.run();
                } catch (Exception e) {
                    log.error("Execute Task Error!", e);
                }
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        int corePoolSize = 10;
        int queueSize = 200;

        SimpleThreadPool pool = new SimpleThreadPool(corePoolSize, queueSize);
        for (int i = 0; i < 100; i++) {
            int idx = i;
            pool.execute(() -> System.err.println("Execute Task-" + idx));
        }

        Thread.sleep(5000L);
        pool.shutdown();
    }
}
