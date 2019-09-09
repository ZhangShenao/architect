package william.spring.bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: ZhangShenao
 * @Date: 2019/5/14 09:52
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        B b1 = applicationContext.getBean(B.class);
        System.err.println(b1.getA());
        B b2 = applicationContext.getBean(B.class);
        System.err.println(b2.getA());
        A a = applicationContext.getBean(A.class);
        System.err.println(a);
    }
}
