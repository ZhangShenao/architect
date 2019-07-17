package william.springboot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import william.springboot.Application;
import william.springboot.dto.UserDto;
import william.springboot.service.UserService;

import java.util.List;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/17 11:00
 * @Description:
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class TestUserService {
    @Autowired
    private UserService userService;

    @Test
    public void testListAll(){
        List<UserDto> dtos = userService.listAll();
        System.err.println(dtos);
    }
}
