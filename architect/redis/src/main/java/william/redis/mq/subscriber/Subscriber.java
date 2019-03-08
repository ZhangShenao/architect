package william.redis.mq.subscriber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import william.redis.config.RedisConfig;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/6 15:15
 * @Description:模拟消息队列——订阅方
 */
@SpringBootApplication
@Import(RedisConfig.class)
public class Subscriber {
    public static void main(String[] args) {
        SpringApplication.run(Subscriber.class, args);
    }
}
