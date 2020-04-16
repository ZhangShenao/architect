package william.spring.autowire;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2019-12-25
 * @Description
 */
@Data
public class UserService {
    //Autowired默认按照类型匹配Bean
    //当匹配到多个类型相同的Bean时,默认按照属性名进行装配
    //当检测到多个类型相同的Bean，且属性名都不匹配时，会抛出异常
    //可以通过@Qualifier指定注入的BeanName
    //也可以使用@Primary注解标识使用哪个Bean
    @Autowired
//    @Qualifier("userDao2")
    private UserDao userDao;
}
