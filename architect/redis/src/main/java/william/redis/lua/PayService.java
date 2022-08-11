package william.redis.lua;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @Auther: ZhangShenao
 * @Date 2022/8/11 11:46 AM
 * @Description: 使用redis lua脚本模拟分布式锁
 * <p>
 * Redis执行Lua脚本。优势
 * 1.Lua脚本可以保证操作的原子性,执行过程中不可被中断,其他操作处于阻塞状态。适合用于执行分布式锁等复杂的逻辑
 * 2.减少多次网络传输的时延
 * 3.可以替代Redis事务,且更简单更高效
 */
@Service
public class PayService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    //lua脚本
    private static final String SCRIPT = " local locked=redis.call('exists',KEYS[1])\n "
            + " if locked==0 then\n "
            + " redis.call('set',KEYS[1],ARGV[1])\n "   //设置lock
            + " redis.call('expire',KEYS[1],ARGV[2])\n "    //设置过期时间
            + " end\n "
            + " return locked\n ";

    public String pay(long orderId) {
        try {
            String lockKey = String.format("lock:%d", orderId);
            String lockValue = "locked";
            String expireSec = "180";
            Long locked = (Long) redisTemplate.execute(RedisScript.of(SCRIPT, Long.class), Collections.singletonList(lockKey), lockValue, expireSec);
            if (locked != null && locked == 0L) {    //加锁成功,执行支付流程
                //模拟支付耗时
                Thread.sleep(10000L);

                //删除锁
                redisTemplate.delete(lockKey);
                return "支付成功";
            }
            return "已经在支付中,请稍后重试";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
