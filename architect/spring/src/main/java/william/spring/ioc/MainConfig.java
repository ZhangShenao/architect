package william.spring.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/18 13:18
 * @Description:
 */
@Configuration
public class MainConfig {
    //通过method注册Bean
    @Bean
    public User user(){
        User u = new User();
        u.setName("James");
        System.err.println("注册User: " + u);
        return u;
    }

    //如果配置类上不标记@Configuration注解,则方法调用会创建一个新的Bean,而不会去IoC容器中查找
    //如果配置类上标价了@Configuration注解,Spring会通过一个ConfigurationClassEnhancer对配置类进行增强,在调用其Bean方法时去容器中获取
    @Bean
    public Group group(){
        Group g = new Group();
        g.setUser(user());
        return g;
    }
}
