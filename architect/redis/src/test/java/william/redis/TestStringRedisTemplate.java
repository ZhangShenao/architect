package william.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/6 10:27
 * @Description: 使用SpringBoot StringRedisTemplate
 */
@SpringBootTest(classes = RedisApplication.class)
@RunWith(SpringRunner.class)
public class TestStringRedisTemplate {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void testSetKey() {
        redisTemplate.opsForValue().set("k1", "v1");
    }


    //Redis Pipeline是将一批命令打包传输并执行,可以大幅降低网络传输开销
    //但是Pipeline没有事务的能力,无法保证原子性。即使Pipeline中某条指令执行异常,后续的指令也仍然会被执行
    @Test
    public void testPipeline() {
        redisTemplate.executePipelined((RedisCallback<Object>) conn -> {
            for (int i = 0; i < 10; i++) {
                String key = "key-" + i;
                String value = "value-" + i;
                conn.set(key.getBytes(), value.getBytes());

                //模拟pipeline中某个命令执行异常
//                if (i == 5) {
//                    conn.incr(key.getBytes());
//                }
            }

            System.out.println(conn.closePipeline());

            return null;
        });
    }
}
