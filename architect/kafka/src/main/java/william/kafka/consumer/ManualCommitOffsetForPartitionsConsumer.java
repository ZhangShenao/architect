package william.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import william.kafka.constant.KafkaConstants;
import java.time.Duration;
import java.util.*;
import static org.apache.kafka.clients.consumer.ConsumerConfig.*;
import static org.apache.kafka.clients.consumer.ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/16 11:07
 * @Description:Partition级别的offset手动提交
 */
public class ManualCommitOffsetForPartitionsConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(GROUP_ID_CONFIG, KafkaConstants.CONSUMER_GROUP_1);

        //关闭位移自动提交,使用手动提交
        props.put(ENABLE_AUTO_COMMIT_CONFIG, "false");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(KafkaConstants.TOPIC_NAME));

        try {
            while (true) {
                //拉取消息
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1L));

                //对拉取到的消息按照分区进行分组
                for (TopicPartition partition : records.partitions()) {
                    //获取指定Partition下的所有消息
                    List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);

                    //对消息进行业务处理
                    for (ConsumerRecord<String, String> record : partitionRecords) {
                        System.err.println(String.format("New Message[topic: %s, partition: %s, offset: %s, key: %s, value: %s]", record.topic(), record.partition(), record.offset(), record.key(), record.value()));
                    }

                    //获取本次拉取到的最后一条消息的offset
                    long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();

                    //提交该Partition的offset,由于提交的位移是下一条待读取的消息,因此offset=lastOffset+1
                    consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset + 1)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }
}
