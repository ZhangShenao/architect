package william.spring.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/12/4 12:21
 * @Description:
 */
@Configuration
public class MainConfig {
    @Bean
//    @Primary
    public Cat cat1(){
        return new Cat();
    }

    @Bean
    public Cat cat2(){
        return new Cat();
    }

    @Bean
    public Zoo zoo(){
        return new Zoo();
    }
}
