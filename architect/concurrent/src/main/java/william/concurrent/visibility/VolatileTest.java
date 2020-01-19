package william.concurrent.visibility;

/**
 * @Author zhangshenao
 * @Date 2020-01-15
 * @Description 使用volatile关键字, 保证变量在多线程间的可见性
 */
public class VolatileTest {
    private static boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (!stop) {
                System.err.println("Task Running...");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(3000L);

        stop = true;
    }
}
