package william.spring.enhance;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/20 13:41
 * @Description:Spring BeanFactory的基本实现
 */
public class BeanFactoryTest {
    @Test
    public void test(){
        Resource resource = new ClassPathResource("applicationContext.xml");
        BeanFactory beanFactory = new XmlBeanFactory(resource);
        MyTestBean bean = (MyTestBean) beanFactory.getBean("myTestBean");
        Assert.assertEquals("testStr",bean.getTestStr());
    }
}
