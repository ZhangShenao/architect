package william.spring.autowire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2019-12-25
 * @Description
 */
@Data
public class UserService {
    @Autowired
    @Qualifier("userDao2")
    private UserDao userDao1;
}
