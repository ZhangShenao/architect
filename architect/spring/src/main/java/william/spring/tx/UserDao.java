package william.spring.tx;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import william.mybatis.annotation.AutoResult;
import william.spring.tx.entity.UserEntity;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/12/11 10:50
 * @Description:
 */
@Component
@AutoResult(UserEntity.class)
@Mapper
public interface UserDao {
}
