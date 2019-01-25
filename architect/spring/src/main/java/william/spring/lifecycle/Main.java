package william.spring.lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/12/4 11:42
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);

        //Get Object
        Zoo zoo = (Zoo) applicationContext.getBean("zoo");
        System.err.println(zoo);

        applicationContext.close();
    }
}
