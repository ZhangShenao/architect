package william.spring.ioc;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/18 13:22
 * @Description:
 */
public class IocTest {
    @Test
    public void testConfiguration(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Group group = applicationContext.getBean("group",Group.class);
    }
}
