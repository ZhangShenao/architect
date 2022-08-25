package william.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import william.redis.bloomfilter.RedissonBloomFilter;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/4/29 下午4:53
 * BloomFilter测试
 */
@SpringBootTest(classes = RedisApplication.class)
@RunWith(SpringRunner.class)
public class TestBloomFilter {
    @Test
    public void testBloomFilter() {
        RedissonBloomFilter<String> bloomFilter = new RedissonBloomFilter<>("test:bf", 1000L, 0.05);
        bloomFilter.initKeys("key-01", "key-02");
        System.out.println("is key-01 exists: " + bloomFilter.exists("key-01"));
        System.out.println("is key-02 exists: " + bloomFilter.exists("key-02"));
        System.out.println("is key-03 exists: " + bloomFilter.exists("key-03"));
    }
}
