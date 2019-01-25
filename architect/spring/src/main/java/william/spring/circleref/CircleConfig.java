package william.spring.circleref;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/12/6 11:10
 * @Description:
 */
@Configuration
@ComponentScan
public class CircleConfig {
    @Bean
    public A a(){
        return new A();
    }

    @Bean
    public B b(){
        return new B();
    }
    @Bean

    public C c(){
        return new C();
    }
}
