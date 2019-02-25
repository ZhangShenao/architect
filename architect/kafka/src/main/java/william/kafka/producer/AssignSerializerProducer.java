package william.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import william.kafka.constant.KafkaConstants;
import william.kafka.pojo.User;
import william.kafka.serializer.UserSerializer;
import java.util.Optional;
import java.util.Properties;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/19 15:31
 * @Description:使用自定义序列化
 */
public class AssignSerializerProducer {
    public static void main(String[] args) {
        Properties props = new Properties();

        //自定义序列化机制
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, UserSerializer.class);

        KafkaProducer<String, User> producer = new KafkaProducer<>(props);

        try {
            User user = new User();
            user.setFirstName("Kobe");
            user.setLastName("Bryant");
            user.setAddress("LosAngelas");
            user.setAge(40);
            ProducerRecord<String, User> message = new ProducerRecord<>(KafkaConstants.TOPIC_NAME, "user", user);

            RecordMetadata metadata = producer.send(message).get();
            System.err.println(String.format("Send Normal Message Result——topic:%s,partition:%s,offset:%s", metadata.topic(), metadata.partition(), metadata.offset()));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Optional.ofNullable(producer).ifPresent(p -> p.close());
        }
    }
}
