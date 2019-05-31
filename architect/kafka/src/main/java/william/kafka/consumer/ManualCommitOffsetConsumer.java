package william.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import william.kafka.constant.KafkaConstants;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/7 17:27
 * @Description:手动提交位移
 */
public class ManualCommitOffsetConsumer {
    private static final int MAX_BATCH_SIZE = 5;

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(GROUP_ID_CONFIG, KafkaConstants.CONSUMER_GROUP);

        //关闭位移自动提交,使用手动提交
        props.put(ENABLE_AUTO_COMMIT_CONFIG,"false");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(KafkaConstants.TOPIC_NAME));

        List<ConsumerRecord<String, String>> buffers = new LinkedList<>();
        try {
            while (true) {
                ConsumerRecords<String, String> messages = consumer.poll(1000L);    //指定拉取消息的超时
                for (ConsumerRecord<String,String> message : messages){
                    buffers.add(message);
                }
                messages.forEach(m -> System.err.println(String.format("New Message[topic: %s, partition: %s, offset: %s, key: %s, value: %s]", m.topic(), m.partition(), m.offset(), m.key(), m.value())));

                //当消息拉取到一定数量时,手动提交位移,提交的offset是本次poll拉取的最大offset
                //如果消息消费成功,但是提交位移是Consumer宕机,则会导致消费位移提交失败,Consumer重启后可能会消费到重复的消息
                if (buffers.size() >= MAX_BATCH_SIZE){
                    persistMessages(buffers);
                    consumer.commitSync();      //同步提交,会自动进行失败重试
                    buffers.clear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }

    private static void persistMessages(List<ConsumerRecord<String, String>> messages){
        System.err.println("消息入库");
    }
}
