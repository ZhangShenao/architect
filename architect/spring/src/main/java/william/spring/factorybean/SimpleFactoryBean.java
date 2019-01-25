package william.spring.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/12/4 11:41
 * @Description:
 */
public class SimpleFactoryBean implements FactoryBean<Cat>{
    @Override
    public Cat getObject() {
        return new Cat();
    }

    @Override
    public Class<?> getObjectType() {
        return Cat.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
