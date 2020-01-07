package william.spring.event;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zhangshenao
 * @Date 2019-12-26
 * @Description
 */
@Configuration
public class MainConfig {
    @Bean
    public RedPackageListener redPackageListener() {
        return new RedPackageListener();
    }
}
