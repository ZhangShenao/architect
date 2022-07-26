package william.springboot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import william.springboot.dto.UserDto;
import william.springboot.service.UserService;

import java.util.List;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/10 18:10
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public UserDto findById(@PathVariable("userId") long userId) {
        return userService.getById(userId);
    }

    @GetMapping("/all")
    public List<UserDto> listAll() {
        return userService.listAll();
    }

    @PostMapping
    public void addUser(@RequestBody UserDto dto) {
        userService.addUser(dto);
    }
}
