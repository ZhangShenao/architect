package william.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

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
        SpringApplication.run(Application.class, args);
    }
}
