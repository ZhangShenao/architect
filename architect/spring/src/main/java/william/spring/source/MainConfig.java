package william.spring.source;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author zhangshenao
 * @Date 2020-04-16
 * @Description
 */
@Configuration
@Import(MyImportBeanDefinitionRegistrar.class) //通过Import的方式导入
@ComponentScan
public class MainConfig {
    //通过@Bean的方式注入
    @Bean
    public UserService userService() {
        return new UserService();
    }
}
