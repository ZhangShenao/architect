package william.guavacache.service;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * @Author zhangshenao
 * @Date 2020-05-21
 * @Description
 */
@Service
public class GuavaCacheService {
    private LoadingCache<String, String> cache;

    @PostConstruct
    public void init() {
        //构建缓存
        cache = CacheBuilder.newBuilder()
                .maximumSize(100L)  //设置缓存最大条目数
                .expireAfterAccess(10L, TimeUnit.SECONDS)    //设置缓存失效时间(在多久未进行读写操作后失效)
                .removalListener(x -> System.err
                        .println("Cache Item was Removed! Key: " + x.getKey() + ", Cause: " + x.getCause() + " "))
                .build(new CacheLoader<String, String>() {      //设置缓存加载机制
                    @Override
                    public String load(String key) {
                        System.err.println("Load Cache, key: " + key);
                        return key + "-value";
                    }
                });
    }

    //保存缓存数据
    //如果缓存中已经有该key,则会先移除这个缓存,这时候会触发RemovalListener监听器,触发之后,再添加这个key和value
    public void put(String key, String value) {
        System.err.println("Put Value, Key: " + key + ", Value: " + value);
        cache.put(key, value);
    }

    //通过key获取缓存value
    //如果key不存在,将调用CacheLoader的load方法再加载其他的数据
    public Optional<String> get(String key) {
        try {
            String value = cache.get(key);
            System.err.println("Get Cache, Key: " + key + ", Value: " + value);
            return Optional.ofNullable(value);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    //移除缓存项,会触发RemovalListener
    public void remove(String key) {
        System.err.println("Remove Key: " + key);
        cache.invalidate(key);
    }

    //清空缓存
    public void clear() {
        System.err.println("Clear Cache");
        cache.invalidateAll();
    }
}
