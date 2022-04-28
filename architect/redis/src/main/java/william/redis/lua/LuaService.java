package william.redis.lua;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/4/28 下午4:43
 * <p>
 * Redis执行Lua脚本。优势
 * 1.Lua脚本可以保证操作的原子性,适合用于执行分布式锁等复杂的逻辑
 * 2.减少多次网络传输的时延
 * 3.可以替代Redis事务,且更简单更高效
 */
@Service
public class LuaService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    //使用Lua脚本模拟减库存操作
    public void reduceStock(String key, int count) {
        redisTemplate.execute((RedisCallback<Object>) conn -> {
            String script = " local count = redis.call('get', KEYS[1]) " +
                    " local a = tonumber(count) " +
                    " local b = tonumber(ARGV[1]) " +
                    " if a >= b then " +
                    "   redis.call('set', KEYS[1], a-b) " +
                    "   return 1 " +
                    " end " +
                    " return 0 ";

            Object result = conn.eval(script.getBytes(), ReturnType.INTEGER, 1, key.getBytes(), String.valueOf(count).getBytes());
            System.out.println("reduce stock result: " + result);
            return result;
        });
    }
}
