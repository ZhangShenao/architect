package william.designpattern.observer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zhangshenao
 * @Date 2020-03-23
 * @Description
 */
@Configuration
public class ObserverConfig {
    @Bean
    public Star star() {
        return new ZhouJieLun();
    }

    @Bean
    public Fans fans() {
        return new ZhangShenAo();
    }
}
