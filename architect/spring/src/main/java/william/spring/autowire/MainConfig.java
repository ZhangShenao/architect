package william.spring.autowire;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/18 13:37
 * @Description:
 */
@Configuration
public class MainConfig {
    @Bean("userService")
    public UserService userService() {
        return new UserService();
    }

    @Bean("userDao1")
    public UserDao userDao1() {
        UserDao dao = new UserDao();
        dao.setNum(1);
        return dao;
    }

    @Bean("userDao2")
    public UserDao userDao2() {
        UserDao dao = new UserDao();
        dao.setNum(2);
        return dao;
    }
}
