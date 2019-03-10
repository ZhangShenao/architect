package william.springboot.service;

import william.springboot.dto.UserDto;

import java.util.List;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/10 18:03
 * @Description:
 */
public interface UserService {
    UserDto getById(long userId);

    void addUser(UserDto dto);

    List<UserDto> listAll();
}
