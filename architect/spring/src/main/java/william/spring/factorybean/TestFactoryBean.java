package william.spring.factorybean;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/12/4 11:42
 * @Description:
 */
public class TestFactoryBean {
    @Test
    public void testFactoryBean(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);

        //Get Object
        /*Object cat = applicationContext.getBean("cat");
        Assert.assertTrue((cat instanceof Cat));*/

        //Get FactoryBean Self
        Object catFactoryBean = applicationContext.getBean(BeanFactory.FACTORY_BEAN_PREFIX + "cat");
        Assert.assertTrue((catFactoryBean instanceof FactoryBean));
    }
}
