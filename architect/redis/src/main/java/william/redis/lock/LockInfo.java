package william.redis.lock;

import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/8 10:29
 * @Description:分布式锁信息
 */
@Getter
@Setter
public class LockInfo {
    //业务唯一id,作为锁的value
    private String transactionId;

    //锁的key
    private String lockKey;

    //重试次数
    private int retryTimes;

    public static LockInfo valueOf(String transactionId, String lockKey) {
        LockInfo info = new LockInfo();
        info.transactionId = transactionId;
        info.lockKey = lockKey;
        info.retryTimes = 0;
        return info;
    }

    public void addRetryTimes(){
        ++retryTimes;
    }
}
