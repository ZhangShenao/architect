package william.spring.importing;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author zhangshenao
 * @Date 2019-12-25
 * @Description
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(MainConfig.class);
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.err::println);
    }
}
