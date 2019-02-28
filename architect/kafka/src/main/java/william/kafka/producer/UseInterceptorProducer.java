package william.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import william.kafka.constant.KafkaConstants;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/19 15:31
 * @Description:使用自定义ProducerInterceptor
 */
public class UseInterceptorProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class); //设置key和value的序列化类型,因为kafka中数据都是字节数组形式。必须指定
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        //构建拦截器链
        List<String> interceptors = new LinkedList<>();
        interceptors.add("william.kafka.interceptor.TimestampPrependerInterceptor");
        interceptors.add("william.kafka.interceptor.CounterInterceptor");
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,interceptors);

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        try {
            for (int i = 0; i < 20; i++) {
                String key = "key-" + i;
                String value = "value" + i;
                ProducerRecord<String, String> message = new ProducerRecord<>(KafkaConstants.TOPIC_NAME, key, value);
                RecordMetadata metadata = producer.send(message).get();
                System.err.println(String.format("Send Message Result——topic:%s,partition:%s,offset:%s", metadata.topic(), metadata.partition(), metadata.offset()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //一定要关闭KafkaProducer,这样才会调用ProducerInterceptor的close()方法
            Optional.ofNullable(producer).ifPresent(p -> p.close());
        }
    }
}
