package william.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import william.kafka.constant.KafkaConstants;

/**
 * @Auther: ZhangShenao
 * @Date 2022/7/26 1:37 PM
 * @Description: SpringBoot Kafka Producer
 */
@Component
public class SpringBootKafkaProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //发送消息
    public void sendMsg(String msg) {
        System.out.println("Send Kafka Message: " + msg);
        kafkaTemplate.send(KafkaConstants.TOPIC_NAME, msg);
    }
}
