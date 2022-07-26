package william.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import william.kafka.constant.KafkaConstants;

/**
 * @Auther: ZhangShenao
 * @Date 2022/7/26 1:39 PM
 * @Description: SpringBoot Kafka Consumer
 */
@Component
public class SpringBootKafkaConsumer {
    @KafkaListener(topics = KafkaConstants.TOPIC_NAME, groupId = KafkaConstants.CONSUMER_GROUP_1)
    public void onMessage(String msg) {
        System.out.println("Consumer Kafka Message: " + msg);
    }
}
