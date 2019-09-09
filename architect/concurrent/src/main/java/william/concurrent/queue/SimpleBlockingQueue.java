package william.concurrent.queue;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/25 16:25
 * @Description:实现简单的阻塞队列
 */
public class SimpleBlockingQueue<T> {
    private static final int DEFAULT_CAPACITY = 64;

    private LinkedList<T> data;
    private int size;
    private int capacity;

    private final Lock lock = new ReentrantLock();
    private final Condition QUEUE_EMPTY = lock.newCondition();
    private final Condition QUEUE_FULL = lock.newCondition();


    public SimpleBlockingQueue() {
        this(DEFAULT_CAPACITY);
    }

    public SimpleBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.data = new LinkedList<>();
        this.size = 0;
    }

    public void put(T t) {
        try {
            lock.lockInterruptibly();
            while (size == capacity) {
                QUEUE_FULL.await();
            }
            data.add(size++, t);
            QUEUE_EMPTY.signalAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public T take() {
        try {
            lock.lockInterruptibly();
            while (size == 0) {
                QUEUE_EMPTY.await();
            }
            QUEUE_FULL.signalAll();
            --size;
            return data.removeFirst();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        try {
            lock.lockInterruptibly();
            StringBuilder builder = new StringBuilder();
            builder.append("[ data: ");
            builder.append(data.toString());
            builder.append(",size: " + size + " ]");
            return builder.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
}
