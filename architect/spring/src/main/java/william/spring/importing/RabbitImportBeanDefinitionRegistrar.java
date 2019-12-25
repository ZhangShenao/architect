package william.spring.importing;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author zhangshenao
 * @Date 2019-12-25
 * @Description 自定义ImportBeanDefinitionRegistrar, 导入指定组件
 */
public class RabbitImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata,
            BeanDefinitionRegistry beanDefinitionRegistry) {
        BeanDefinition beanDefinition = new RootBeanDefinition(Rabbit.class);
        beanDefinitionRegistry.registerBeanDefinition("rabbit", beanDefinition);
    }
}
