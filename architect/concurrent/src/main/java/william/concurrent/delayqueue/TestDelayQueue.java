package william.concurrent.delayqueue;

import java.util.concurrent.DelayQueue;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/30 14:25
 * @Description:
 */
public class TestDelayQueue {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayTask> delayQueue = new DelayQueue<>();
        delayQueue.put(new DelayTask());

        Thread thread = new Thread(() -> {
            try {
                DelayTask task = delayQueue.take();
                task.run();
            } catch (InterruptedException e) {
            }
        });
        thread.start();
        Thread.sleep(Long.MAX_VALUE);
    }

}
