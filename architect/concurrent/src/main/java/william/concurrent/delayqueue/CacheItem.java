package william.concurrent.delayqueue;

import lombok.Getter;
import lombok.Setter;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/27 18:34
 * @Description:缓存项
 */
@Getter
@Setter
public class CacheItem<K,V> implements Delayed{
    private K key;
    private V value;
    private long expireTimeMillis;

    @Override
    public long getDelay(TimeUnit unit) {
        return expireTimeMillis - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        CacheItem item = (CacheItem)o;
        return (expireTimeMillis > item.expireTimeMillis ? 1 : 0);
    }
}
