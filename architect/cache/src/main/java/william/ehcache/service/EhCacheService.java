package william.ehcache.service;

import java.io.FileNotFoundException;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * @Author zhangshenao
 * @Date 2020-05-21
 * @Description
 */
@Service
public class EhCacheService {
    private CacheManager cacheManager;

    @PostConstruct
    public void init() throws FileNotFoundException {
        //构建CacheManager
        String path = ResourceUtils.getFile("classpath:ehcache.xml").getPath();
        cacheManager = new CacheManager(path);
    }

    public void print() {
        cacheManager.addCacheIfAbsent("cache_test");
        Cache cache = cacheManager.getCache("cache_test");

        cache.put(new Element("firstCode", "第一个元素"));
        Element element = cache.get("firstCode");

        System.err.println("获取到的元素是: " + element);

        int diskStoreSize = cache.getDiskStoreSize();
        System.err.println("diskStoreSize:" + diskStoreSize);

    }
}
