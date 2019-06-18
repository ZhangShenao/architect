package william.spring.autowire;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import william.spring.bean.Cat;
import william.spring.bean.Dog;
import william.spring.bean.Monkey;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/18 13:37
 * @Description:
 */
@Configuration
public class MainConfig {
    @Bean
    public Cat cat() {
        return new Cat();
    }

    @Bean
    public Dog dog() {
        return new Dog();
    }

    @Bean
    public Monkey monkey() {
        return new Monkey();
    }
}
