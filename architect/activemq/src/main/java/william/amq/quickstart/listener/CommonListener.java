package william.amq.quickstart.listener;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/6 21:44
 * @Description:
 */
public class CommonListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.err.println("接收到消息: " + message);
    }
}
