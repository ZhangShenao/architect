package william.springboot.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import william.springboot.Application;
import william.springboot.entity.UserEntity;
import william.springboot.mapper.UserMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/10 17:47
 * @Description:
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testCreate() {
        UserEntity entity = new UserEntity();
        entity.setName("James");
        entity.setAge(34);
        userMapper.insert(entity);
        Assert.assertTrue(entity.getId() > 0L);
    }

    @Test
    public void testListByAge(){
        List<UserEntity> entities = userMapper.listByAge(34);
        List<UserEntity> entities2 = userMapper.listByAge(34);
        Optional.ofNullable(entities).orElse(Collections.emptyList()).forEach(System.err::println);
    }
}
