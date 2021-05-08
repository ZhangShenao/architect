package william.redis.optimize.lua;

import java.util.Collections;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author zhangshenao
 * @Date 2020-02-05
 * @Description 执行Lua脚本
 * <p>
 * Redis的Lua脚本可以保证原子性和事务，支持命令执行失败自动回滚
 * 此外,Lua脚本还可以有效减少网络开销,提升性能
 */
public class TestLua {
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

        //初始化商品10016的库存
        jedis.set("product_stock_10016", "15");

        //执行Lua脚本
        String script = " local count = redis.call('get', KEYS[1]) " +
                " local a = tonumber(count) " +
                " local b = tonumber(ARGV[1]) " +
                " if a >= b then " +
                "   redis.call('set', KEYS[1], count-b) " +
//                                "   bb == 0 " +     //模拟语法报错回滚操作
                "   return 1 " +
                " end " +
                " return 0 ";

        //获取执行结果
        Object result = jedis.eval(script, Collections.singletonList("product_stock_10016"),
                Collections.singletonList("10"));
        System.err.println(result);
    }

}
