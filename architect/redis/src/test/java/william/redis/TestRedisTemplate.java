package william.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/6 10:27
 * @Description:
 */
@SpringBootTest(classes = RedisApplication.class)
@RunWith(SpringRunner.class)
public class TestRedisTemplate {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void testSetKey(){
        redisTemplate.opsForValue().set("k1","v1");
    }

}
