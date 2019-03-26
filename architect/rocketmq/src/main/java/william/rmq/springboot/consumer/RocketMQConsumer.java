package william.rmq.springboot.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import william.rmq.springboot.property.RocketMQProperty;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Optional;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/26 13:28
 * @Description:
 */
@Component
public class RocketMQConsumer implements MessageListenerConcurrently {
    @Autowired
    private RocketMQProperty property;

    private DefaultMQPushConsumer consumer;

    @PostConstruct
    private void init() throws Exception {
        consumer = new DefaultMQPushConsumer();
        consumer.setNamesrvAddr(property.getNamesrvAddr());
        consumer.setConsumerGroup(property.getConsumerGroup());
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setMessageModel(MessageModel.CLUSTERING);
        consumer.subscribe(property.getTopic(), "*");
        consumer.registerMessageListener(this);
        consumer.start();
        System.err.println("Consumer 启动");
    }

    @PreDestroy
    private void shutdown() {
        Optional.ofNullable(consumer).ifPresent(DefaultMQPushConsumer::shutdown);
        System.err.println("Consumer 关闭");
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        int i = 0;
        try {
            for (; i < msgs.size(); i++) {
                MessageExt message = msgs.get(i);
                String body = new String(message.getBody(), RemotingHelper.DEFAULT_CHARSET);
                System.err.println(String.format("监听到消息,messageId: %s, topic: %s, tags: %s, body: %s ", message.getMsgId(), message.getTopic(), message.getTags(), body));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (i < msgs.size()) {
                context.setAckIndex(i + 1);
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
