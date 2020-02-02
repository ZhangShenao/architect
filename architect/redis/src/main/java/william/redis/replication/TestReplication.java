package william.redis.replication;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author zhangshenao
 * @Date 2020-02-02
 * @Description Redis主从复制
 */
public class TestReplication {
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

        //执行命令
        System.err.println(jedis.set("single", "single"));
        System.err.println(jedis.get("single"));
    }
}
