package william.redis.controller;

import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhangshenao
 * @Date 2020-02-03
 * @Description
 * Redisson实现的分布式锁,是可重入的。原理：
 * 1. 基于Redis的Hash结构,创建分布式锁。key=用户传入的lockKey, field=id:threadId value=锁的重入次数
 * 2. 锁有默认30s的超时时间,获取锁成功后,Redisson会创建定时任务,检查锁的ttl,定时为锁续约,避免任务未执行完成就释放了锁
 * 3. 获取锁失败的线程,会进入自旋等待
 *
 */
@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private Redisson redisson;  //Redisson客户端

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String STOCK_KEY_FORMAT = "stock:%s";

    private static final String LOCK_KEY_FORMAT = "stock:lock:%s";

    private static final String LOCK_VALUE_FORMAT = "stock:lock:%s:%s";

    private static final long DEFAULT_LOCK_TTL_IN_SECONDS = 30L;    //锁默认30s释放

    @GetMapping("/reduce_by_redisson/{skuId}")
    public String reduceStockByRedisson(@PathVariable("skuId") String skuId) {
        String lockKey = createLockKey(skuId);
        RLock lock = redisson.getLock(lockKey);
        try {
            //加锁
            lock.lock();

            //扣减库存
            String key = String.format(STOCK_KEY_FORMAT, skuId);
            String stock = redisTemplate.opsForValue().get(key);
            if (StringUtils.isEmpty(stock) || Integer.parseInt(stock) <= 0) {
                return "商品已售罄";
            }
            int value = Integer.parseInt(stock) - 1;
            redisTemplate.opsForValue().set(key, String.valueOf(value));
        } finally {
            //释放锁
            lock.unlock();
        }

        return "SUCCESS";

    }

    @GetMapping("/reduce/{skuId}")
    public String reduceStock(@PathVariable("skuId") String skuId) {
        String lockKey = createLockKey(skuId);
        String lockValue = createLockValue(skuId);
        try {
            //加锁,并设置默认超时时间
            redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue);
            redisTemplate.expire(lockKey, DEFAULT_LOCK_TTL_IN_SECONDS, TimeUnit.SECONDS);

            //扣减库存
            String key = String.format(STOCK_KEY_FORMAT, skuId);
            String stock = redisTemplate.opsForValue().get(key);
            if (StringUtils.isEmpty(stock) || Integer.parseInt(stock) <= 0) {
                return "商品已售罄";
            }
            int value = Integer.parseInt(stock) - 1;
            redisTemplate.opsForValue().set(key, String.valueOf(value));
        } finally {
            //释放锁
            if (lockValue.equals(redisTemplate.opsForValue().get(lockKey))) {
                //判断锁是否为当前线程加的,避免锁被其他线程释放
                redisTemplate.delete(lockKey);
            }
        }

        return "SUCCESS";

    }

    private String createLockKey(String skuId) {
        return String.format(LOCK_KEY_FORMAT, skuId);
    }

    private String createLockValue(String skuId) {
        return String.format(LOCK_VALUE_FORMAT, skuId, Thread.currentThread().getId());
    }

}
