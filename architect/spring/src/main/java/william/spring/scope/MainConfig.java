package william.spring.scope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 * @Author zhangshenao
 * @Date 2019-12-25
 * @Description
 */
public class MainConfig {
    @Bean
    @Scope("singleton") //指定Bean的作用域,默认为单例
    @Lazy   //延迟初始化,在第一个获取Bean的时候才创建Bean实例
    public Person person(){
        return new Person();
    }
}
