package william.pattern;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;

/**
 * @Author: ZhangShenao
 * @Date: 2019/5/22 10:22
 * @Description:基于读写锁实现的简单的缓存 1.读操作之间可并发执行
 * 2.读写、写写操作之间互斥
 * 3.根据key获取不同的锁,减小锁粒度
 */
public class SimpleCache<K, V> {
    private Map<K, V> cache = new HashMap<>();
    private Map<K, ReadWriteLock> locks = new ConcurrentHashMap<>();

    public V get(K key, Supplier<V> optionsWhenMiss) {
        V value;
        try {
            //加读锁
            acquireLock(key, false);

            //查询缓存
            value = cache.get(key);
        } finally {
            //释放读锁
            releaseLock(key, false);
        }

        //如果缓存命中,则直接返回
        if (value != null) {
            return value;
        }

        //缓存未命中,加写锁,执行optionsWhenMiss操作
        try {
            acquireLock(key, true);
            
            //这里再进行一次缓存的查询,避免高并发场景下,多线程竞争写锁,导致数据的重复查询
            value = cache.computeIfAbsent(key, k -> optionsWhenMiss.get());
        } finally {
            //释放写锁
            releaseLock(key, true);
        }
        return value;
    }

    public void put(K key, V value) {
        //加写锁
        try {
            acquireLock(key, true);
            cache.put(key, value);
        } finally {
            //释放写锁
            releaseLock(key, true);
        }
    }

    private void acquireLock(K key, boolean write) {
        Lock lock = lockForKey(key, write);
        lock.lock();
    }

    private void releaseLock(K key, boolean write) {
        Lock lock = lockForKey(key, write);
        lock.unlock();

    }

    private Lock lockForKey(K key, boolean write) {
        ReadWriteLock rw = locks.computeIfAbsent(key, k -> new ReentrantReadWriteLock());
        return write ? rw.writeLock() : rw.readLock();
    }
}
