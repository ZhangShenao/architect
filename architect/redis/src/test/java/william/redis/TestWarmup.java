package william.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import william.redis.warmup.JedisPoolWarmup;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/4/29 下午5:36
 */
@SpringBootTest(classes = RedisApplication.class)
@RunWith(SpringRunner.class)
public class TestWarmup {
    @Test
    public void testWarmup() {
        JedisPoolWarmup warmup = new JedisPoolWarmup();
        warmup.warmup();
    }
}
