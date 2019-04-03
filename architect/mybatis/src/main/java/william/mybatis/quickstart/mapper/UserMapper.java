package william.mybatis.quickstart.mapper;

import org.apache.ibatis.annotations.Param;
import william.mybatis.quickstart.entity.UserEntity;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/12/7 11:51
 * @Description:
 */
public interface UserMapper {

    UserEntity selectById(@Param("id") long userId);

    void insert(UserEntity userEntity);

    void update(UserEntity userEntity);
}
