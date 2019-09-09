package william.spring.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import william.spring.bean.Cat;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/30 16:33
 * @Description:自定义BeanDefinitionRegistryPostProcessor
 * 是一个特殊的BeanFactoryPostProcessor,主要用于手动向容器中注册BeanDefinition
 */
public class MyBeanFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor{
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        BeanDefinition beanDefinition = new RootBeanDefinition(Cat.class);
        registry.registerBeanDefinition("cat",beanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
