package william.amq.spring.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import william.amq.spring.constant.MQConstants;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/8 16:24
 * @Description:
 */
@Service
public class JmsService {
    @JmsListener(destination = MQConstants.SAMPLE_TOPIC_NAME,
            containerFactory = "topicListenerContainerFactory")
    public void onMessageFromTopic1(String payload) {
        System.err.println("On Message From Topic,payload: " + payload);
    }

    @JmsListener(destination = MQConstants.SAMPLE_TOPIC_NAME,
            containerFactory = "topicListenerContainerFactory")
    public void onMessageFromTopic2(String payload) {
        System.err.println("On Message From Topic,payload: " + payload);
    }

    @JmsListener(destination = MQConstants.SAMPLE_QUEUE_NAME,
            containerFactory = "queueListenerContainerFactory")
    public void onMessageFromQueue1(String payload) {
        System.err.println("On Message From Queue,payload: " + payload);
    }

    @JmsListener(destination = MQConstants.SAMPLE_QUEUE_NAME,
            containerFactory = "queueListenerContainerFactory")
    public void onMessageFromQueue2(String payload) {
        System.err.println("On Message From Queue,payload: " + payload);
    }
}
