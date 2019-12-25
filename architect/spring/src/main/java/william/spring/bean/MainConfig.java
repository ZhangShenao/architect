package william.spring.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: ZhangShenao
 * @Date: 2019/5/14 09:50
 * @Description:
 */
@Configuration
@ComponentScan
public class MainConfig {
    @Bean("person1")   //注册一个Bean,可以指定beanName。如果未指定,则使用方法名作为beanName
    public Person person() {
        return new Person();
    }
}
