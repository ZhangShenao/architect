package william.redis.warmup;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import william.redis.consts.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/4/29 下午5:31
 * <p>
 * Jedis连接池预热
 */
public class JedisPoolWarmup {
    public void warmup() {
        JedisPoolConfig config = new JedisPoolConfig();  //构建JedisPoolConfig连接池配置
        config.setMaxTotal(20);   //最大连接数
        config.setMaxIdle(10);     //最大空闲连接数
        config.setMinIdle(5);   //最小空闲连接数
        config.setMaxWaitMillis(5000L); //最大等待时间,如果超过这个时间没有获取到连接,就会抛出异常
        config.setTestOnBorrow(false);  //是否对连接进行校验(ping)
        JedisPool jedisPool = new JedisPool(config, Constants.HOST, Constants.PORT);//创建JedisPool,同时会建立连接

        List<Jedis> conns = new ArrayList<>(config.getMinIdle());

        //使用redis ping命令,对连接池进行预热,初始化minIdle个连接
        for (int i = 0; i < config.getMinIdle(); i++) {
            Jedis conn = jedisPool.getResource();
            conn.ping();    //ping
            conns.add(conn);
        }

        //将预热好的连接交还给连接池
        conns.forEach(Jedis::close);

        System.out.println("jedis pool warmup finished");
    }
}
