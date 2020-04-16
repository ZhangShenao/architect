package william.spring.postprocessor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author zhangshenao
 * @Date 2020-04-16
 * @Description
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(MainConfig.class);
        System.err.println("注册后,BeanDefinition数量: " + applicationContext.getBeanDefinitionCount());
    }
}
