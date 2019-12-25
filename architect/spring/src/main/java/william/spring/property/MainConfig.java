package william.spring.property;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author zhangshenao
 * @Date 2019-12-25
 * @Description
 */
@Configuration
@PropertySource("classpath:person.properties")
public class MainConfig {
    @Bean
    public Person person() {
        return new Person();
    }
}
