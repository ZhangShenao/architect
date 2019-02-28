package william.concurrent.delayqueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/27 18:38
 * @Description:缓存实现,可自动移除过期的缓存项
 */
public class ScheduledCache<K, V> {
    private final DelayQueue<CacheItem<K, V>> cache = new DelayQueue<>();

    private final int capacity;

    private AtomicInteger size;

    private volatile boolean valid;

    public ScheduledCache(int capacity) {
        this.capacity = capacity;
        size = new AtomicInteger(0);
        valid = true;
        startCheckTask();
    }

    public void put(K key, V value, long timeout) {
        CacheItem item = new CacheItem();
        item.setKey(key);
        item.setValue(value);
        item.setExpireTimeMillis(System.currentTimeMillis() + timeout);
        cache.put(item);
        size.incrementAndGet();
        System.err.println(String.format("添加缓存项。key: %s, value: %s。", item.getKey(), item.getValue()));
    }

    public void evict(){
        size.compareAndSet(size.get(),0);
        valid = false;
        cache.clear();
    }

    private class CheckExpiredItemTask implements Runnable {
        @Override
        public void run() {
            while (valid) {
                try {
                    expire(cache.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void startCheckTask(){
        new Thread(new CheckExpiredItemTask()).start();
    }

    private void expire(CacheItem item) {
        size.decrementAndGet();
        System.err.println(String.format("缓存项已过期!key: %s, value: %s, 缓存剩余项数量: %s。", item.getKey(), item.getValue(), size.get()));
    }
}
