package william.concurrent.pattern.guardedsuspension;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/**
 * @Author zhangshenao
 * @Date 2021-03-21
 * @Description 实现Guarded-Suspension模式的关键对象
 * 本质上是管程模型的等待-唤醒机制
 */
public class GuardedObject<T> {
    //维护全局的GuardedObject的对象，方便客户端根据key关联到GuardedObject对象
    private static final ConcurrentHashMap<String, GuardedObject> objs = new ConcurrentHashMap<>();

    //受保护的对象
    private T guarded;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition done = lock.newCondition();
    private final long timeoutInSecs;

    public GuardedObject(long timeoutInSecs) {
        this.timeoutInSecs = timeoutInSecs;
    }

    public static <E> GuardedObject<E> create(String key, long timeoutInSecs) {
        GuardedObject<E> obj = new GuardedObject<>(timeoutInSecs);
        objs.put(key, obj);
        return obj;
    }

    public static <E> void fireEvent(String key, E e) {
        GuardedObject obj = objs.get(key);
        if (obj == null) {
            throw new RuntimeException("GuardedObject not Exists! key: " + key);
        }
        obj.onChanged(e);
    }

    //获取受保护的对象,当条件不满足时阻塞线程
    public T get(Predicate<T> p) {
        try {
            lock.lockInterruptibly();
            while (!p.test(guarded)) {
                done.await(timeoutInSecs, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();

            //返回受保护的对象
            return guarded;
        }
    }

    //条件满足时的事件通知
    private void onChanged(T t) {
        try {
            lock.lockInterruptibly();
            //赋值受保护的对象
            this.guarded = t;
            done.signalAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
