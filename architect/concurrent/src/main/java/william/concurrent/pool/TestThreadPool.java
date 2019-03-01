package william.concurrent.pool;

import java.util.concurrent.*;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/1 15:47
 * @Description:使用自定义线程池
 */
public class TestThreadPool {
    public static void main(String[] args) {
        ThreadPoolExecutor boundedPool = new ThreadPoolExecutor(1, 2, 10L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2), new SimpleThreadFactory(), new LoggingRejectedHandler()){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                super.beforeExecute(t, r);
                System.err.println("Before Execute,threadName: " + t.getName());
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r,t);
                System.err.println("After Execute,");
            }
        };
        ThreadPoolExecutor unboundedPool = new ThreadPoolExecutor(2, 2, 10L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(), new SimpleThreadFactory(), new LoggingRejectedHandler());

        for (int i = 0; i < 5; i++) {
            boundedPool.submit(new Task(i));
//            unboundedPool.submit(new Task(i));
        }

        boundedPool.shutdown();
        unboundedPool.shutdown();
    }


    private static class Task implements Runnable {
        private int id;

        public Task(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.err.println("Execute Task,threadName: " + Thread.currentThread().getName() + ",taskId: " + id);
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "[Task: " + id + "]";
        }
    }

    private static class LoggingRejectedHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.err.println("Reject Task : " + r);
        }
    }

    private static class SimpleThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "Thread-Task");
        }
    }
}
