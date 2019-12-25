package william.spring.scope;

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
        Person person1 = applicationContext.getBean("person", Person.class);
        Person person2 = applicationContext.getBean("person", Person.class);
        System.err.println(person1 == person2);
    }
}
