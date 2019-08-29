package william.concurrent.thread;

/**
 * @Author zhangshenao
 * @Date 2019-08-29
 * @Description 中断sleep状态的线程
 */
public class InterruptSleptThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(100L);
                    System.err.println("interrupt flag: " + Thread.currentThread().isInterrupted());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    //sleep中的线程被中断,此时JVM在抛出InterruptedException后,会清除线程的中断标志位
                    //因此如果想让线程终止,需要再次执行interrupt()
                    Thread.currentThread().interrupt();
                    System.err.println("interrupt flag: " + Thread.currentThread().isInterrupted());
                }
            }
        });

        t.start();

        Thread.sleep(2000L);

        //中断sleep的线程
        t.interrupt();
    }
}
