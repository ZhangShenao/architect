package william.rmq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import william.rmq.common.RocketMQConstants;
import william.rmq.listener.OrderlyListener;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/26 20:47
 * @Description:顺序消息的消费端
 */
public class OrderConsumer {
    public static void main(String[] args) {
        try {
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(RocketMQConstants.CONSUMER_GROUP);
            consumer.setNamesrvAddr(RocketMQConstants.NAMESRV_ADDR);
            consumer.setMessageModel(MessageModel.CLUSTERING);
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.subscribe(RocketMQConstants.TOPIC_NAME, "*");
            consumer.registerMessageListener(new OrderlyListener());
            consumer.start();
            System.err.println("Consumer Start");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
