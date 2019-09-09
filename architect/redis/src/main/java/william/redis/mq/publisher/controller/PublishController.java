package william.redis.mq.publisher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import william.redis.config.MessageDto;
import william.redis.consts.Constants;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/6 15:17
 * @Description:
 */
@RestController
public class PublishController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/send")
    public void sendMessage(@RequestBody MessageDto dto) {
        redisTemplate.convertAndSend(Constants.MESSAGE_TOPIC,dto.getPayload());
        System.err.println("Send Message: " + dto.getPayload());
    }
}
