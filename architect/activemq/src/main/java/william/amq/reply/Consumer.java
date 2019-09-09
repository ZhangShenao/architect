package william.amq.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import william.amq.common.Constants;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/12 18:42
 * @Description:Request-Response模式的响应端
 */
@Service
public class Consumer {
    @Autowired
    @Qualifier(Constants.JMS_QUEUE_TEMPLATE)
    private JmsTemplate queueTemplat;

    @JmsListener(destination = Constants.QueueNames.REQUEST_QUEUE, containerFactory = Constants.QUEUE_LISTENER_CONTAINER_FACTORY)
    public void onRequest(TextMessage message) throws JMSException {
        //获取消息回复目的地和关联id，向回复目的地发送回复消息
        Destination replyTo = message.getJMSReplyTo();
        ResponseDto response = new ResponseDto();
        response.setUid(message.getJMSCorrelationID());
        response.setSuccess(true);
        queueTemplat.convertAndSend(replyTo,response);
    }
}
