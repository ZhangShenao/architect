package william.spring.factorybean;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/12/4 11:42
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);

        //Get Object
        Cat cat = (Cat) applicationContext.getBean("simpleFactoryBean");
        System.err.println(cat);

        //Get FactoryBean Self
        SimpleFactoryBean simpleFactoryBeanSelf = (SimpleFactoryBean) applicationContext.getBean(BeanFactory.FACTORY_BEAN_PREFIX + "simpleFactoryBean");
        System.err.println(simpleFactoryBeanSelf);
    }
}
