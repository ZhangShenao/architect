package william.spring.aop;

import org.springframework.stereotype.Component;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/12/5 16:47
 * @Description:
 */
@Component
public class Calculator {
    public void divide(int x,int y){
        System.err.println("Calculate Divide: " + (x / y));
    }
}
