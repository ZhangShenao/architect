package william.redis.lock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/8 14:13
 * @Description:自定义Lock,标记在方法上,用于指定哪些方法需要自动加Redis锁
 */
@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Lock {
    String lockKey() default "";

    long expireSeconds();

    long timeoutSeconds();
}
