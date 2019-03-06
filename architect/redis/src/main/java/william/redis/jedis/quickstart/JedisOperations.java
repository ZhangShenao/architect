package william.redis.jedis.quickstart;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import william.redis.consts.Constants;

import java.util.Set;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/6 08:07
 * @Description:使用Jedis操作Redis
 */
public class JedisOperations {
    private JedisPool jedis;

    @Before
    public void init() {
        //构建JedisPoolConfig连接池配置
        JedisPoolConfig config = new JedisPoolConfig();

        //最大空闲连接数
        config.setMaxIdle(5);

        //最大连接数
        config.setMaxTotal(10);

        //最大等待时间,如果超过这个时间没有获取到连接,就会抛出异常
        config.setMaxWaitMillis(5000L);

        //是否对连接进行校验
        config.setTestOnBorrow(false);

        //创建JedisPool,同时会建立连接
        jedis = new JedisPool(config, Constants.HOST, Constants.PORT);
    }

    @Test
    public void operationsForKeys() {
        Jedis resource = jedis.getResource();
        String v1 = resource.get("k1");
        System.err.println(v1);
        resource.set("k2", "v2");
    }

    @Test
    public void operationsForSet(){
        Jedis resource = jedis.getResource();
        resource.sadd("s1","aaa","bbb","ccc");
        resource.smembers("s1").forEach(System.err::println);
    }

    @After
    public void close() {
        jedis.close();
    }
}
