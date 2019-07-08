package william.spring.aop;

import org.springframework.stereotype.Component;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/7 11:32
 * @Description:
 */
@Component
public class SimpleCalculator implements Calculator{
    public void divide(int x,int y){
        System.err.println("Calculate Divide: " + (x / y));
    }
}
