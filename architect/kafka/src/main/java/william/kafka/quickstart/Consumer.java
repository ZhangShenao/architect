package william.kafka.quickstart;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import william.kafka.constant.KafkaConstants;
import java.time.Duration;
import java.util.Collections;
import java.util.Optional;
import java.util.Properties;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/2 14:04
 * @Description:Kafka消费者
 */
public class Consumer {
    public static void main(String[] args) throws Exception {
        //Producer属性配置
        Properties props = new Properties();

        //设置Kafka服务器地址，集群环境下应该至少配置两个Broker地址
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");

        //设置key和value的反序列化类型，因为kafka中数据都是字节数组形式
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        //设置消费者组
        props.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConstants.TOPIC_NAME);

        //创建Consumer实例
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        //订阅主题
        consumer.subscribe(Collections.singletonList(KafkaConstants.TOPIC_NAME));

        //拉取消息 Kafka只有拉取消息模式，没有推送模式
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000L);
            Optional.ofNullable(records).orElse(ConsumerRecords.empty()).forEach(record -> System.err.println("接收到消息,key: " + record.key() + ",value: " + record.value() + ",topic: " + record.topic() + ",partition: " + record.partition() + ",offset: " + record.offset()));
        }
    }
}
