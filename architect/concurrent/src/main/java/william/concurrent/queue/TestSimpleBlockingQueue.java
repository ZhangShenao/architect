package william.concurrent.queue;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/25 16:55
 * @Description:
 */
public class TestSimpleBlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<String> queue = new SimpleBlockingQueue<>(5);

        Thread putThread = new Thread(() -> {
            for (int i = 0;i < 100000;i++){
                queue.put(String.valueOf(i));
                System.err.println(queue);
            }
        });
        putThread.start();

        Thread.sleep(2000L);

        Thread takeThread = new Thread(() -> {
            while (true){
                queue.take();
                /*try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                System.err.println(queue);
            }
        });
        takeThread.start();

    }
}
