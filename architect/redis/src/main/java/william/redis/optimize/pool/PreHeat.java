package william.redis.optimize.pool;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author zhangshenao
 * @Date 2020-02-05
 * @Description Redis连接池预热
 * 如果系统启动完马上就会有很多的请求过来，那么可以给redis连接池做预热，比如快速的创建一些redis连接，执行简单命令，类似ping()，
 * 快速的将连接池里的空闲连接提升到minIdle的数量。
 */
public class PreHeat {
    //保存预热连接
    private List<Jedis> preHeatJedis = new ArrayList<>();

    //连接池预热
    public void preHeat() {
        //构造JedisPool连接池
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(10);
        config.setMinIdle(5);
        JedisPool jedisPool = new JedisPool(config, "127.0.0.1", 6379);

        //服务启动时,将连接池中的连接数,预热到minIdle的数量。
        for (int i = 0; i < config.getMinIdle(); i++) {
            Jedis jedis = jedisPool.getResource();

            //执行ping命令,建立连接
            jedis.ping();

            //保存连接
            preHeatJedis.add(jedis);

            System.err.println("PreHead Jedis: " + jedis);
        }
    }

    //释放连接池资源
    public void release() {
        preHeatJedis.forEach(Jedis::close);
        System.err.println("Release All Jedis!");
    }

    public static void main(String[] args) throws InterruptedException {
        PreHeat preHeat = new PreHeat();
        preHeat.preHeat();
        Thread.sleep(5000L);
        preHeat.release();
    }
}
