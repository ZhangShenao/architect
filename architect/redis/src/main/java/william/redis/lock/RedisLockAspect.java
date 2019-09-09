package william.redis.lock;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.util.Optional;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/8 14:05
 * @Description:Redis锁切面,拦截标记了@Lock注解的方法,为其执行自动加锁和释放锁的操作
 */
@Aspect
@Component
public class RedisLockAspect {
    @Autowired
    private RedisLock redisLock;

    @Around("@annotation(lock)")
    public Object addLockForMethods(ProceedingJoinPoint point, Lock lock) throws Throwable {
        Optional<LockInfo> lockInfo = Optional.empty();
        String lockKey = generateLockKey(point, lock);
        Object result = null;
        try {
            lockInfo = redisLock.tryLock(lockKey, lock.expireSeconds(), lock.timeoutSeconds());
            result = point.proceed();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lockInfo.ifPresent(l -> redisLock.release(lockKey));
        }
        return result;
    }

    private String generateLockKey(ProceedingJoinPoint point, Lock lock) {
        if (StringUtils.hasText(lock.lockKey())) {
            return lock.lockKey();
        }
        MethodSignature signature = (MethodSignature) point.getSignature();
        String methodName = signature.getMethod().getName();
        String className = signature.getMethod().getDeclaringClass().getCanonicalName();
        return methodName + "-" + className;
    }
}
