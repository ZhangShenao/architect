package william.concurrent.queue;

import java.util.concurrent.SynchronousQueue;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/25 18:02
 * @Description:
 */
public class TestSynchronousQueue {
    public static void main(String[] args) {
        SynchronousQueue<String> queue = new SynchronousQueue<>();

        //SynchronousQueue必须在多线程环境下使用,其内部没有容量,不保存任何数据,一个线程的插入操作必须等待另一个线程的移除操作。
        new Thread(() -> {
            try {
                String s = queue.take();
                System.err.println(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                queue.add("a");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }
}
