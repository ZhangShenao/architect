package william.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import static org.apache.kafka.clients.consumer.ConsumerConfig.*;
import static william.kafka.constant.KafkaConstants.CONSUMER_GROUP_1;
import static william.kafka.constant.KafkaConstants.STANDALONE_TOPIC_NAME;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/16 12:02
 * @Description:独立消费者
 */
public class StandaloneConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(GROUP_ID_CONFIG, CONSUMER_GROUP_1);
        props.put(MAX_POLL_INTERVAL_MS_CONFIG,10000);          //Consumer两次poll的最大时间间隔,即Consumer处理逻辑的最大时间
        props.put(AUTO_OFFSET_RESET_CONFIG, "earliest");        //当Consumer端无offset信息时,从哪里开始消费
        props.put(ENABLE_AUTO_COMMIT_CONFIG, "true");           //是否自动提交位移
        props.put(FETCH_MAX_BYTES_CONFIG,10485760);             //Consumer端单次获取消息的最大字节数
        props.put(MAX_POLL_RECORDS_CONFIG,5);                   //单次poll调用所返回的最大消息数
        props.put(AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");      //Consumer自动提交offset的时间间隔

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        //获取指定topic下的partition信息
        List<TopicPartition> partitions = consumer.partitionsFor(STANDALONE_TOPIC_NAME).stream()
                .map(p -> new TopicPartition(STANDALONE_TOPIC_NAME, p.partition()))
                .collect(Collectors.toList());

        //为独立消费者指定partition
        consumer.assign(partitions);


        try {
            while (true) {
                ConsumerRecords<String, String> messages = consumer.poll(Duration.ofSeconds(1L));    //指定拉取消息的超时
                messages.forEach(m -> System.err.println(String.format("New Message[topic: %s, partition: %s, offset: %s, key: %s, value: %s]", m.topic(), m.partition(), m.offset(), m.key(), m.value())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }

    }
}
