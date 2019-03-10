package william.springboot.mapper;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import william.springboot.entity.UserEntity;
import java.util.List;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/10 17:36
 * @Description:
 */
public interface UserMapper extends Mapper<UserEntity>, MySqlMapper<UserEntity> {
    List<UserEntity> listByAge(@Param("age") int age);
}
