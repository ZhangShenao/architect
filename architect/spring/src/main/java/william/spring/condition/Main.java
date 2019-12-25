package william.spring.condition;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * @Author zhangshenao
 * @Date 2019-12-25
 * @Description
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.err::println);
    }
}
