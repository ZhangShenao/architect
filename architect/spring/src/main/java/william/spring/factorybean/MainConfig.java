package william.spring.factorybean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/12/4 11:40
 * @Description:
 */
@Configuration
public class MainConfig {
    @Bean(name = "cat")
    public CatFactoryBean simpleFactoryBean(){
        return new CatFactoryBean();
    }
}
