package william.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.errors.RetriableException;
import org.apache.kafka.common.serialization.StringSerializer;
import william.kafka.constant.KafkaConstants;

import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/19 15:31
 * @Description:简单的kafka Producer
 */
public class SimpleProducer {
    public static void main(String[] args) {
        //Step1:构造Properties对象,指定Producer的一些属性
        Properties props = new Properties();
        //设置Kafka服务器地址,必须指定。如果Kafka集群中机器数很多,那么只需要指定部分Broker即可,不需要列出所有的机器。因为不管指定几台机器,Producer都会通过该参数找到井发现集群中所有的Broker
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        //因为Kafka Broker端的消息都为字节数组格式,因此必须为消息的key和value指定序列化机制。(必须使用全限定类名)
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class); //设置key和value的序列化类型,因为kafka中数据都是字节数组形式。必须指定
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        //Optional
        props.put(ProducerConfig.ACKS_CONFIG, "-1");    //指定Broker端消息持久化机制
        props.put(ProducerConfig.RETRIES_CONFIG, 3);    //对于可重试异常,指定消息重试次数
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 323840);    //消息发送Batch的大小
        props.put(ProducerConfig.LINGER_MS_CONFIG, 10);         //消息发送的延时时间
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);   //指定Producer端用于缓存消息的缓冲区大小
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 3000);

        //Step2:使用构造好的Properties对象创建KafkaProducer实例
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        try {
            for (int i = 0; i < 100; i++) {
                String key = "key-" + i;
                String value = "value" + i;
                //Step3:构造待发送的消息对象ProducerRecord,指定要发送到的Topic、Partition、key和value
                ProducerRecord<String, String> message = new ProducerRecord<>(KafkaConstants.TOPIC_NAME, key, value);

                //Step4:发送消息(异步)
                producer.send(message, (metadata, exception) -> {
                    //消息发送成功
                    if (exception == null && metadata != null) {
                        System.err.println(String.format("Send Message Result——topic:%s,partition:%s,offset:%s", metadata.topic(), metadata.partition(), metadata.offset()));
                        return;
                    }

                    //可重试异常
                    if (exception instanceof RetriableException) {
                        //TODO 处理可重试瞬时异常
                        return;
                    }

                    //TODO 不可重试异常
                    System.err.println("Send Message Result——metadata: " + metadata + ",exception: " + exception);
                });

                //Step4:发送消息(同步)
                RecordMetadata metadata = producer.send(message).get();
                System.err.println(String.format("Send Message Result——topic:%s,partition:%s,offset:%s", metadata.topic(), metadata.partition(), metadata.offset()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //Step5:关闭KafkaProducer。Producer程序运行结束后,一定要关闭KafkaProducer
            Optional.ofNullable(producer).ifPresent(p -> p.close());
        }
    }
}
