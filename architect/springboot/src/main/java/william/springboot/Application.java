package william.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;
import william.springboot.entity.UserEntity;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/10 10:42
 * @Description:
 */
@SpringBootApplication
@EnableScheduling       //开启定时任务调度
@EnableRetry            //开启重试机制
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        UserEntity entity1 = applicationContext.getBean(UserEntity.class);
        UserEntity entity2 = applicationContext.getBean(UserEntity.class);
        System.err.println(entity1);
        System.err.println(entity2);
    }
}
