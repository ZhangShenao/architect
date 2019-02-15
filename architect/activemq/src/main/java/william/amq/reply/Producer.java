package william.amq.reply;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import william.amq.common.Constants;
import javax.jms.TextMessage;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/12 18:09
 * @Description:Request-Response模式的请求端
 */
@Service
public class Producer {
    //保存所有请求的业务id和响应结果
    private static final Map<String, Boolean> replies = new ConcurrentHashMap<>();

    @Autowired
    @Qualifier(Constants.JMS_QUEUE_TEMPLATE)
    private JmsTemplate queueTemplat;

    public void sendMessage(RequestDto dto) {
        queueTemplat.send(Constants.QueueNames.REQUEST_QUEUE, session -> {
            TextMessage message = session.createTextMessage(dto.getPayload());

            //设置消息关联id，将请求和应答消息关联起来
            message.setJMSCorrelationID(dto.getUid());

            //设置消息回复的目的地
            message.setJMSReplyTo(new ActiveMQQueue(Constants.QueueNames.RESPONSE_QUEUE));

            //记录发送的请求
            replies.putIfAbsent(dto.getUid(), false);
            return message;
        });
    }

    @JmsListener(destination = Constants.QueueNames.RESPONSE_QUEUE, containerFactory = Constants.QUEUE_LISTENER_CONTAINER_FACTORY)
    public void onReply(ResponseDto dto) {
        replies.put(dto.getUid(), dto.isSuccess());
        System.err.println("On Reply: " + dto);
    }
}
