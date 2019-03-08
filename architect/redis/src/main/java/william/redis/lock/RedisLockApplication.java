package william.redis.lock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import william.redis.config.RedisConfig;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/8 13:49
 * @Description:
 */
@SpringBootApplication
@Import(RedisConfig.class)
@EnableAspectJAutoProxy
public class RedisLockApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(RedisLockApplication.class, args);
        DemoService demoService = applicationContext.getBean(DemoService.class);

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            System.err.println(demoService.demoTask());
        }

        executorService.shutdown();
    }
}
