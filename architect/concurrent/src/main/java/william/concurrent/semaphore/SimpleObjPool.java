package william.concurrent.semaphore;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

/**
 * @Author zhangshenao
 * @Date 2021-03-15
 * @Description 简单的对象池, 基于Semaphore实现限流器
 */
public class SimpleObjPool<T> {
    //使用队列维护对象
    private CopyOnWriteArrayList<T> queue;

    //通过信号量实现限流器
    private Semaphore semaphore;

    //私有化构造器
    private SimpleObjPool() {
    }

    public static <T> SimpleObjPool<T> newInstance(int size, T obj) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size Must be Positive!");
        }
        SimpleObjPool<T> pool = new SimpleObjPool<>();
        pool.queue = new CopyOnWriteArrayList<>();
        for (int i = 0; i < size; i++) {
            pool.queue.add(obj);
        }
        pool.semaphore = new Semaphore(size);
        return pool;
    }

    public <R> R exec(Function<T, R> func) {
        T t = null;
        try {
            semaphore.acquire();
            t = queue.remove(0);
            Thread.sleep(ThreadLocalRandom.current().nextInt(0, 500));
            return func.apply(t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            queue.add(t);
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        SimpleObjPool<String> pool = SimpleObjPool.newInstance(10, "test");
        for (int i = 0; i < 100; i++) {
            int idx = i;
            new Thread(() -> {
                String result = pool.exec(s -> String.format("Exec Thread-%s", idx));
                System.err.println(result);
            }).start();
        }
    }
}
