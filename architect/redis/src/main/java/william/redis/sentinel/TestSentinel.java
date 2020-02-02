package william.redis.sentinel;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author zhangshenao
 * @Date 2020-02-02
 * @Description 哨兵架构
 */
@Component
public class TestSentinel {
    private static final Logger logger = LoggerFactory.getLogger(TestSentinel.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostConstruct
    public void setRedisKeys() throws InterruptedException {
        int i = 0;
        while (true) {
            String key = "key-" + i;
            String value = String.valueOf(i++);
            redisTemplate.opsForValue().set(key, value);
            System.err.println("Set Redis Key: " + key);
            Thread.sleep(2000L);
        }
    }
}
