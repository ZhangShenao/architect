package william.rmq.springboot.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import william.rmq.springboot.property.RocketMQProperty;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Optional;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/26 13:14
 * @Description:
 */
@Component
public class RocketMQProducer {
    @Autowired
    private RocketMQProperty property;

    private DefaultMQProducer producer;

    @PostConstruct
    private void init() throws MQClientException {
        producer = new DefaultMQProducer();
        producer.setNamesrvAddr(property.getNamesrvAddr());
        producer.setProducerGroup(property.getProducerGroup());
        producer.start();
        System.err.println("Producer 启动");
    }

    @PreDestroy
    private void destroy() {
        Optional.ofNullable(producer).ifPresent(DefaultMQProducer::shutdown);
        System.err.println("Producer 关闭");
    }

    public SendResult sendMessageSync(Message message) throws Exception {
        return producer.send(message);
    }

    public void sendMessageAsync(Message message, SendCallback callback) throws Exception {
        producer.send(message, callback);
    }

}
