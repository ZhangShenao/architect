package william.spring.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/12/5 16:58
 * @Description:
 */
public class AopMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfig.class);
        Calculator calculator = applicationContext.getBean(Calculator.class);
        calculator.divide(2,0);
    }
}
