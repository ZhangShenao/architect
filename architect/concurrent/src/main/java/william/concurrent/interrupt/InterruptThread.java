package william.concurrent.interrupt;

/**
 * @Author zhangshenao
 * @Date 2020-01-13
 * @Description
 */
public class InterruptThread {
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            //判断线程的中断标志位
            while (!Thread.currentThread().isInterrupted()) {
                System.err.println("count: " + count++);
            }
            System.err.println("isInterrupt: " + Thread.currentThread().isInterrupted());

            //清除中断标志位
            Thread.interrupted();

            System.err.println("isInterrupt: " + Thread.currentThread().isInterrupted());
        });
        t.start();

        Thread.sleep(2000L);

        //中断线程
        t.interrupt();
    }
}
