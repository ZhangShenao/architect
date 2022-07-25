package william.springboot.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @Auther: ZhangShenao
 * @Date 2022/7/25 2:00 PM
 * @Description: Kafka Producer
 */
@Component
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //设置Topic
//    @Bean
//    public NewTopic topic() {
//        return TopicBuilder.name("quickstart-events")
//                .partitions(1)
//                .replicas(1)
//                .build();
//    }

    public void send(String data) {
        kafkaTemplate.send("quickstart-events", data);
    }
}
