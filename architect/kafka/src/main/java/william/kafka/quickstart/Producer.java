package william.kafka.quickstart;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import william.kafka.constant.KafkaConstants;
import java.util.Properties;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/2 13:30
 * @Description:Kafka生产者
 */
public class Producer {
    public static void main(String[] args) throws Exception {
        //Producer属性配置
        Properties props = new Properties();

        //设置Kafka服务器地址，集群环境下应该至少配置两个Broker地址
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");

        //设置key和value的序列化类型，因为kafka中数据都是字节数组形式
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);


        //创建生产者实例，指定key和value类型和配置文件
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        //创建ProducerRecord,表示一个消息实例
        ProducerRecord<String, String> record = new ProducerRecord<>(KafkaConstants.TOPIC_NAME, "name", "James");

        //发送消息——异步
        producer.send(record);

        //发送消息——同步
        producer.send(record,(metadata, exception) -> System.err.println("partition: " + metadata.partition() + ",offset: " + metadata.offset()));

        System.err.println("消息发送成功");
        producer.close();
    }
}
