package william.redis.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/8 10:26
 * @Description:基于Redis实现的分布式锁
 */
@Component
public class RedisLock {
    @Autowired
    private StringRedisTemplate redisTemplate;

    private ValueOperations<String, String> opsForValue;

    private ThreadLocal<String> localTransactionId = new ThreadLocal<>();

    @PostConstruct
    private void init() {
        opsForValue = redisTemplate.opsForValue();
    }

    public Optional<LockInfo> tryLock(String lockKey, long timeoutMillis, long expireSeconds) throws InterruptedException {
        Assert.isTrue(timeoutMillis > 0L, "timeout must be positive!!");
        Assert.isTrue(expireSeconds > 0L, "expire must be positive!!");

        String transactionId = UUID.randomUUID().toString();
        localTransactionId.set(transactionId);
        LockInfo lockInfo = LockInfo.valueOf(transactionId, lockKey);

        for (long startTime = System.currentTimeMillis(); System.currentTimeMillis() - startTime < timeoutMillis; lockInfo.addRetryTimes()) {
            Boolean success = opsForValue.setIfAbsent(lockKey, transactionId);
            if (Boolean.TRUE.equals(success)) {
                redisTemplate.expire(lockKey, expireSeconds, TimeUnit.SECONDS);
                System.err.println(String.format("Thread fetch lock. transactionId: %s, lockKey: %s, retryTimes: %s", lockInfo.getTransactionId(), lockKey, lockInfo.getRetryTimes()));
                return Optional.of(lockInfo);
            }
            Thread.sleep(20L);
        }

        //timeout
        System.err.println(String.format("Thread fetch lock timeout!! transactionId: %s, lockKey: %s", lockInfo.getTransactionId(), lockKey));
        return Optional.empty();

    }

    public void release(String lockKey) {
        String transactionId = Optional.ofNullable(opsForValue.get(lockKey)).orElseThrow(() -> new IllegalStateException("Lock Expired!! key: " + lockKey));
        if (!transactionId.equalsIgnoreCase(localTransactionId.get())) {
            throw new IllegalStateException("Thread try to release lock, but lock is not held by this thread!! threadId: " + Thread.currentThread().getId() + ", lockKey: " + lockKey);
        }
        redisTemplate.delete(lockKey);
        System.err.println(String.format("Thread release lock. transactionId: %s, lockKey: %s", transactionId, lockKey));
    }
}
