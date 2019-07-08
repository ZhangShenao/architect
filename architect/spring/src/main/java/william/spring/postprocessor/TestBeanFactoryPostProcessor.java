package william.spring.postprocessor;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;
import william.spring.bean.Cat;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/30 16:36
 * @Description:
 */
public class TestBeanFactoryPostProcessor {
    @Test
    public void testBeanFactoryPostProcessor(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Cat cat = applicationContext.getBean("cat", Cat.class);
        Assert.notNull(cat);
    }
}
