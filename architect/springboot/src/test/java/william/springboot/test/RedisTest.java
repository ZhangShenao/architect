package william.springboot.test;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import william.springboot.Application;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/10 17:47
 * @Description:
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class RedisTest {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void testSetKey() {
        redisTemplate.opsForValue().set("k1", "v1");
        String value = redisTemplate.opsForValue().get("k1");
        Assert.assertEquals(value, "v1");
    }
}
