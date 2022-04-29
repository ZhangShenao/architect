package william.redis.bloomfilter;

import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.Arrays;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/4/29 下午4:46
 * 使用Redisson提供的BloomFilter
 * <p>
 * BloomFilter如果判断一个key存在,那么该key大概率是存在的。如果判断一个key不存在,那么就是一定不存在
 * 如果BloomFilter中的key频繁修改或者删除,可能导致BloomFilter失效,需要定期重建。
 * BloomFilter适用于数据变更不频繁、大数据量且对精度和实时性要求不高的场景
 */
public class RedissonBloomFilter<T> {
    public RBloomFilter<T> bloomFilter;

    /**
     * 创建BloomFilter
     */
    public RedissonBloomFilter(String name, long expectedInsertions, double falseProbability) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        RedissonClient redissonClient = Redisson.create(config);
        RBloomFilter<T> filter = redissonClient.getBloomFilter(name);
        filter.tryInit(expectedInsertions, falseProbability);
        this.bloomFilter = filter;
    }

    /**
     * 初始化key
     */
    public void initKeys(T... keys) {
        Arrays.stream(keys).forEach(x -> {
            bloomFilter.add(x);
        });
    }

    /**
     * 判断指定key是否存在
     */
    public boolean exists(T key) {
        return bloomFilter.contains(key);
    }
}
