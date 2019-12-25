package william.spring.importing;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author zhangshenao
 * @Date 2019-12-25
 * @Description
 */
@Configuration
//通过Import注入其他组件
@Import({Cat.class, DogImportSelector.class, RabbitImportBeanDefinitionRegistrar.class})
public class MainConfig {

}
