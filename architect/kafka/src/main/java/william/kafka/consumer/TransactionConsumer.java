package william.kafka.consumer;

import static org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.ISOLATION_LEVEL_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG;
import static william.kafka.constant.KafkaConstants.CONSUMER_GROUP_1;
import static william.kafka.constant.KafkaConstants.TOPIC_NAME;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

/**
 * @Author zhangshenao
 * @Date 2020-06-29
 * @Description 事务Consumer
 */
public class TransactionConsumer {
    private static final String ISOLATION_LEVEL = "read_committed";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(GROUP_ID_CONFIG, CONSUMER_GROUP_1);


        //事务Consumer,设置isolation.level,只消费已经commit的消息
        //read_committed: 表明Consumer只会读取事务型Producer成功提交事务写入的消息。当然了,它也能看到非事务型Producer写入的所有消息。
        props.put(ISOLATION_LEVEL_CONFIG, ISOLATION_LEVEL);


        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        consumer.subscribe(Collections.singletonList(TOPIC_NAME));

        try {
            while (true) {
                ConsumerRecords<String, String> messages = consumer.poll(Duration.ofSeconds(1L));    //指定拉取消息的超时

                messages.forEach(m -> System.err.println(
                        String.format("New Message[topic: %s, partition: %s, offset: %s, key: %s, value: %s]",
                                m.topic(), m.partition(), m.offset(), m.key(), m.value())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }

    }
}
