package william.redis.lock;

import org.springframework.stereotype.Service;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/8 14:28
 * @Description:
 */
@Service
public class DemoService {
    @Lock(expireSeconds = 60L, timeoutSeconds = 5L)
    public String demoTask() {
        return "Do demo task...";
    }
}
