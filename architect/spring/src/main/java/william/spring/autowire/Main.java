package william.spring.autowire;

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
        UserService userService = applicationContext.getBean(UserService.class);
        System.err.println(userService.getUserDao1().getNum());
    }
}
