package william.springboot.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import william.springboot.dto.UserDto;
import william.springboot.entity.UserEntity;
import william.springboot.mapper.UserMapper;
import william.springboot.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/10 18:05
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto getById(long userId) {
        UserEntity entity = userMapper.selectByPrimaryKey(userId);
        if (entity == null) {
            return null;
        }
        return entity2Dto(entity);
    }

    @Override
    public void addUser(UserDto dto) {
        UserEntity entity = dto2Entity(dto);
        userMapper.insert(entity);
    }

    @Override
    @Transactional
    public List<UserDto> listAll() {
        List<UserEntity> entities = userMapper.selectAll();
        return Optional
                .ofNullable(entities)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::entity2Dto).collect(Collectors.toList());
    }

    private UserDto entity2Dto(UserEntity entity) {
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    private UserEntity dto2Entity(UserDto dto) {
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
