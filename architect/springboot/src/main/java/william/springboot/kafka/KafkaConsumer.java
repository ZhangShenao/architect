package william.springboot.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

/**
 * @Auther: ZhangShenao
 * @Date 2022/7/25 1:57 PM
 * @Description: Kafka Consumer
 */
@Component
public class KafkaConsumer {
    //设置Topic
    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("quickstart-events")
                .partitions(1)
                .replicas(1)
                .build();
    }

    //注册Kafka消息监听器
    @KafkaListener(id = "consumer-group-1", topics = "quickstart-events")
    public void listen(String in) {
        System.out.println("Consume Kafka Message: " + in);
    }
}
