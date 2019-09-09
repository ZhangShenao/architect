package william.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import static org.apache.kafka.clients.consumer.ConsumerConfig.*;
import static william.kafka.constant.KafkaConstants.*;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/26 14:25
 * @Description:简单的Kafka Consumer
 * KafkaConsumer是一个双线程的设计,创建KafkaConsumer的线程成为用户主线程,poll方法就在用户主线程当中运行。
 * 另外还有一个心跳线程。
 * KafkaConsumer不是线程安全的
 */
public class SimpleConsumer {
    public static void main(String[] args) {
        //Step1:构造Properties对象,配置Consumer相关属性
        Properties props = new Properties();
        //Required
        props.put(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(GROUP_ID_CONFIG, CONSUMER_GROUP_1);

        //Optional
//        props.put(SESSION_TIMEOUT_MS_CONFIG,5000);             //Coordinator检测组内成员失效的时间
        props.put(MAX_POLL_INTERVAL_MS_CONFIG,10000);          //Consumer两次poll的最大时间间隔,即Consumer处理逻辑的最大时间
        props.put(AUTO_OFFSET_RESET_CONFIG, "earliest");        //当Consumer端无offset信息时,从哪里开始消费
        props.put(ENABLE_AUTO_COMMIT_CONFIG, "true");           //是否自动提交位移
        props.put(FETCH_MAX_BYTES_CONFIG,10485760);             //Consumer端单次获取消息的最大字节数
        props.put(MAX_POLL_RECORDS_CONFIG,5);                   //单次poll调用所返回的最大消息数
        props.put(AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");      //Consumer自动提交offset的时间间隔

        //Step2:使用构建好的Properties对象,创建KafkaConsumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        //Step3:调用KafkaConsumer的subscribe方法,订阅感兴趣的Topic集合
        consumer.subscribe(Collections.singletonList(TOPIC_NAME));

        try {
            while (true) {
                //Step4:循环调用poll方法,拉取消息,该方法会从所有订阅的Topic中并行地拉取多个分区的消息
                ConsumerRecords<String, String> messages = consumer.poll(Duration.ofSeconds(1L));    //指定拉取消息的超时

                //Step5:对拉取到的消息进行处理
                messages.forEach(m -> System.err.println(String.format("New Message[topic: %s, partition: %s, offset: %s, key: %s, value: %s]", m.topic(), m.partition(), m.offset(), m.key(), m.value())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //Step6:关闭KafkaConsumer
            //Consumer程序结束后一定要显式关闭以释放KafkaConsumer运行过程中占用的各种系统资源(比如线程资源、内存、Socket连接等)
            //同时会通知消费者组Coordinator主动离组,同时更快地开启新一轮rebalance。
            consumer.close();
        }

    }
}
