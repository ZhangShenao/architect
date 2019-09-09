package william.redis.mq.publisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import william.redis.config.RedisConfig;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/6 15:14
 * @Description:模拟消息队列——发送方
 */
@SpringBootApplication
@Import(RedisConfig.class)
public class Publisher {
    public static void main(String[] args) {
        SpringApplication.run(Publisher.class, args);
    }
}
