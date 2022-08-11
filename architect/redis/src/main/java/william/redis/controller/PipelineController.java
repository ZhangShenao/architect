package william.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

/**
 * @Auther: ZhangShenao
 * @Date 2022/8/11 5:32 PM
 * @Description: 测试Redis管道性能
 */
@RestController
public class PipelineController {
    private static final int KEY_COUNT = 10000;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/write_without_pipeline")
    public long writeWithoutPipeline() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < KEY_COUNT; i++) {
            redisTemplate.opsForValue().set("key-" + (i + 1), String.valueOf((i + 1)));
        }
        return System.currentTimeMillis() - startTime;
    }

    @GetMapping("/write_by_pipeline")
    public long writeByPipeline() {
        long startTime = System.currentTimeMillis();
        redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection conn) throws DataAccessException {
                for (int i = 0; i < KEY_COUNT; i++) {
                    conn.stringCommands().set(("key-" + (i + 1)).getBytes(StandardCharsets.UTF_8), String.valueOf((i + 1)).getBytes(StandardCharsets.UTF_8));
                }
                return null;
            }
        });
        return System.currentTimeMillis() - startTime;
    }
}
