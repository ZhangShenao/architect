package william.kafka.producer;

import static org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.TRANSACTIONAL_ID_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;
import static william.kafka.constant.KafkaConstants.TOPIC_NAME;

import java.util.Optional;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * @Author zhangshenao
 * @Date 2020-06-29
 * @Description 事务Producer
 */
public class TransactionProducer {
    private static final String PRODUCER_TX_ID = "1";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        //开启事务Producer
        props.put(ENABLE_IDEMPOTENCE_CONFIG, "true");
        props.put(TRANSACTIONAL_ID_CONFIG, PRODUCER_TX_ID);

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        //初始化事务
        producer.initTransactions();

        //开启事务
        producer.beginTransaction();
        try {

            //在事务中发送消息
            for (int i = 0; i < 10; i++) {
                String key = "key-" + i;
                String value = "value" + i;
                ProducerRecord<String, String> message = new ProducerRecord<>(TOPIC_NAME, key, value);

                RecordMetadata metadata = producer.send(message).get();
                System.err.println(
                        String.format("Send Message Result——topic:%s,partition:%s,offset:%s", metadata.topic(),
                                metadata.partition(), metadata.offset()));

                //模拟发送异常
                if (i >= 4) {
                    throw new RuntimeException("模拟消息发送异常");
                }
            }

            //发送成功,提交事务
            producer.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();

            //发送异常,回滚事务,事务Consumer无法消费本次当前事务内发送的消息
            producer.abortTransaction();
        } finally {
            //Step5:关闭KafkaProducer。Producer程序运行结束后,一定要关闭KafkaProducer
            Optional.of(producer).ifPresent(KafkaProducer::close);
        }
    }
}
