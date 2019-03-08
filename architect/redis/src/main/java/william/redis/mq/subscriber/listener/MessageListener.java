package william.redis.mq.subscriber.listener;

import org.springframework.stereotype.Component;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/6 16:13
 * @Description:
 */
@Component
public class MessageListener {
    public void onMessage(String payload){
        System.err.println("On Message: " + payload);
    }
}
