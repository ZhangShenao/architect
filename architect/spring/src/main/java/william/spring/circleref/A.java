package william.spring.circleref;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/12/6 11:09
 * @Description:
 */
public class A {
    @Autowired
    private B b;
}
