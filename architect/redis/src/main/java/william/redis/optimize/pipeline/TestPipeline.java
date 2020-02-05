package william.redis.optimize.pipeline;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

/**
 * @Author zhangshenao
 * @Date 2020-02-05
 * @Description 使用Pipeline批量执行Redis命令
 * Pipeline并不是原子操作，也不具备事务能力，只是将多条命令打包执行，减少逐条执行带来的网络开销
 */
public class TestPipeline {
    public static void main(String[] args) {
        //Jedis配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(10);
        config.setMinIdle(5);

        //构造JedisPool连接池
        JedisPool jedisPool = new JedisPool(config, "127.0.0.1", 6379);

        //从连接池中获取一个Redis客户端连接
        Jedis jedis = jedisPool.getResource();

        //创建Pipeline
        Pipeline pipelined = jedis.pipelined();

        //将多条命令打包
        for (int i = 0; i < 10; i++) {
            pipelined.incr("pipelineKey");
            pipelined.set("key-" + i, "value" + i);
        }

        //执行pipeline
        List<Object> results = pipelined.syncAndReturnAll();
        System.err.println(results);

    }
}
