package william.spring.profile;

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
        DataSource ds = applicationContext.getBean(DataSource.class);
        System.err.println(ds);
    }
}
