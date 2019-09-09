package william.spring.circleref;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/12/6 11:10
 * @Description:
 */
public class CircleRefMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(CircleConfig.class);
        A a = applicationContext.getBean(A.class);
        System.err.println(a);
    }
}
