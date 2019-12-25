package william.spring.condition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

/**
 * @Author zhangshenao
 * @Date 2019-12-25
 * @Description
 */
public class MainConfig {
//    @Bean
    public Relax relax() {
        return new Relax();
    }

    @Bean
    public Work work() {
        return new Work();
    }

    @Bean
    @Conditional(LifeCondition.class)   //使用自定义Condition,满足某些条件才注入Bean
    public Life life() {
        return new Life();
    }

}
