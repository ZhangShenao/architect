package william.spring.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

import william.spring.bean.Cat;

/**
 * @Author zhangshenao
 * @Date 2020-04-16
 * @Description BeanDefinitionRegistryPostProcessor, 扩展BeanFactoryPostProcessor, 可以向容器中注册自定义的BeanDefinition
 */
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.err.println("MyBeanDefinitionRegistryPostProcessor: postProcessBeanDefinitionRegistry");
        System.err.println("注册前,BeanDefinition数量: " + registry.getBeanDefinitionCount());

        //注册自定义的BeanDefinition
        registry.registerBeanDefinition("cat", new RootBeanDefinition(Cat.class));
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
