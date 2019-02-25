package william.kafka.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/25 14:43
 * @Description:自定义Produer拦截器——记录消息发送成功和失败的次数
 */
public class CounterInterceptor implements ProducerInterceptor<String, String> {
    private AtomicInteger successCount;
    private AtomicInteger errorCount;

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if (exception == null) {
            successCount.incrementAndGet();
        } else {
            errorCount.incrementAndGet();
        }
    }

    @Override
    public void close() {
        System.err.println("发送成功的数量: " + successCount.get());
        System.err.println("发送失败的数量: " + errorCount.get());
    }

    @Override
    public void configure(Map<String, ?> configs) {
        successCount = new AtomicInteger(0);
        errorCount = new AtomicInteger(0);
    }
}
