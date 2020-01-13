package william.concurrent.interrupt;

/**
 * @Author zhangshenao
 * @Date 2020-01-13
 * @Description 处于阻塞、等待状态的线程,被中断后,JVM会先将线程的中断标志位置位,然后抛出InterruptedException异常
 */
public class TestInterruptedException {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(Long.MAX_VALUE);
                } catch (InterruptedException e) {
                    System.err.println("Thread is Interrupted!");
                    System.err.println("isInterrupt: " + Thread.currentThread().isInterrupted());
                    e.printStackTrace();
                }
            }
        });
        t.start();
        Thread.sleep(2000L);
        t.interrupt();
    }
}
