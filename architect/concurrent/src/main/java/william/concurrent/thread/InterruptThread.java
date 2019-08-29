package william.concurrent.thread;

/**
 * @Author zhangshenao
 * @Date 2019-08-28
 * @Description 通过interrupt()中断标志为终止线程
 */
public class InterruptThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            //判断中断标志位
            while (!Thread.currentThread().isInterrupted()) {
                System.err.println("interrupt flag: " + Thread.currentThread().isInterrupted());
                System.err.println("do something...");
            }

            //注: Thread.interrupted()方法会清除中断标志位
            /*while (!Thread.interrupted()){
                System.err.println("interrupt flag: " + Thread.currentThread().isInterrupted());
                System.err.println("do something...");
            }*/

            System.err.println("interrupt flag: " + Thread.currentThread().isInterrupted());
        });
        t.start();
        Thread.sleep(2000L);

        //中断线程
        t.interrupt();
    }
}
