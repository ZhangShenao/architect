package william.spring.autowire;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import william.spring.bean.Zoo;
import static org.springframework.beans.factory.support.AbstractBeanDefinition.*;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/18 13:38
 * @Description:
 */
public class TestAutowire {
    //通过构造器自动注入
    @Test
    public void testAutowireByConstructor(){
        //创建ApplicationContext
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);

        //获取BeanFactory
        DefaultListableBeanFactory beanFactory = applicationContext.getDefaultListableBeanFactory();

        //注册BeanDefinition
        RootBeanDefinition beanDefinition = new RootBeanDefinition(Zoo.class);
        //自动注入类型:构造器贪婪模式,会通过参数最多的构造器创建Bean。如果匹配到多个,则优先选择第一个
        //如果没有设置此参数,会尝试通过默认无参构造器实例化Bean。如果不存在默认无参构造器,且存在多个带参构造器时会抛出异常
        beanDefinition.setAutowireMode(AUTOWIRE_CONSTRUCTOR);
        beanFactory.registerBeanDefinition("zoo",beanDefinition);

        //获取Bean
        Zoo zoo = beanFactory.getBean("zoo", Zoo.class);
        System.err.println(zoo);
    }

    //通过setter自动注入
    @Test
    public void testAutowireBySetter(){
        //创建ApplicationContext
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);

        //获取BeanFactory
        DefaultListableBeanFactory beanFactory = applicationContext.getDefaultListableBeanFactory();

        //注册BeanDefinition
        RootBeanDefinition beanDefinition = new RootBeanDefinition(Zoo.class);
        //根据类型注入,只要setter匹配到类型即可
//        beanDefinition.setAutowireMode(AUTOWIRE_BY_TYPE);

        //根据名称注入,必须严格遵循setter命名规范
        beanDefinition.setAutowireMode(AUTOWIRE_BY_NAME);
        beanFactory.registerBeanDefinition("zoo",beanDefinition);

        //获取Bean
        Zoo zoo = beanFactory.getBean("zoo", Zoo.class);
        System.err.println(zoo);
    }
}
