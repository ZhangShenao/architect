package william.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import william.kafka.constant.KafkaConstants;
import william.kafka.partitioner.AuditPartitioner;

import java.util.Optional;
import java.util.Properties;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/19 15:31
 * @Description:使用自定义分区器
 */
public class AssignPartitionerProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        //使用自定义分区器
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, AuditPartitioner.class);
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        try {
            String auditKey = AuditPartitioner.AUDIT_KEY;
            String normalKey = "key";
            String value = "value";
            ProducerRecord<String, String> normalMessage = new ProducerRecord<>(KafkaConstants.TOPIC_NAME, normalKey, value);
            ProducerRecord<String, String> auditMessage = new ProducerRecord<>(KafkaConstants.TOPIC_NAME, auditKey, value);

            RecordMetadata metadata = producer.send(normalMessage).get();
            System.err.println(String.format("Send Normal Message Result——topic:%s,partition:%s,offset:%s", metadata.topic(), metadata.partition(), metadata.offset()));

            metadata = producer.send(normalMessage).get();
            System.err.println(String.format("Send Normal Message Result——topic:%s,partition:%s,offset:%s", metadata.topic(), metadata.partition(), metadata.offset()));

            metadata = producer.send(normalMessage).get();
            System.err.println(String.format("Send Normal Message Result——topic:%s,partition:%s,offset:%s", metadata.topic(), metadata.partition(), metadata.offset()));

            metadata = producer.send(auditMessage).get();
            System.err.println(String.format("Send Audit Message Result——topic:%s,partition:%s,offset:%s", metadata.topic(), metadata.partition(), metadata.offset()));

            metadata = producer.send(auditMessage).get();
            System.err.println(String.format("Send Audit Message Result——topic:%s,partition:%s,offset:%s", metadata.topic(), metadata.partition(), metadata.offset()));

            metadata = producer.send(auditMessage).get();
            System.err.println(String.format("Send Audit Message Result——topic:%s,partition:%s,offset:%s", metadata.topic(), metadata.partition(), metadata.offset()));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Optional.ofNullable(producer).ifPresent(p -> p.close());
        }
    }
}
