package william.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import william.kafka.constant.KafkaConstants;

import java.util.Arrays;
import java.util.Properties;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/26 14:25
 * @Description:简单的Kafka Consumer
 */
public class SimpleConsumer {
    public static void main(String[] args) {
        //Step1:构造Properties对象,配置Consumer相关属性
        Properties props = new Properties();
        //Required
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConstants.CONSUMER_GROUP);
        //Optional
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        //Step2:使用构建好的Properties对象,创建KafkaConsumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        //Step3:调用KafkaConsumer的subscribe方法,订阅感兴趣的Topic集合
        consumer.subscribe(Arrays.asList(KafkaConstants.TOPIC_NAME));

        try {
            while (true) {
                //Step4:循环调用poll方法,拉取消息,该方法会从所有订阅的Topic中并行地拉取多个分区的消息
                ConsumerRecords<String, String> messages = consumer.poll(1000L);    //指定拉取消息的超时

                //Step5:对拉取到的消息进行处理
                messages.forEach(m -> System.err.println(String.format("New Message[topic: %s, partition: %s, offset: %s, key: %s, value: %s]", m.topic(), m.partition(), m.offset(), m.key(), m.value())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //Step6:关闭KafkaConsumer
            //Consumer程序结束后一定要显式关闭以释放KafkaConsumer运行过程中占用的各种系统资源(比如线程资源、内存、Socket连接等)
            consumer.close();
        }

    }
}
