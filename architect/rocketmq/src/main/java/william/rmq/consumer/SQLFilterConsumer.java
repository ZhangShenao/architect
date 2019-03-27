package william.rmq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import william.rmq.common.RocketMQConstants;
import william.rmq.listener.SimpleListener;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/27 14:27
 * @Description:通过SQL表达式对Message进行过滤——Consumer端
 */
public class SQLFilterConsumer {
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        consumer.setNamesrvAddr(RocketMQConstants.NAMESRV_ADDR);
        consumer.setConsumerGroup(RocketMQConstants.CONSUMER_GROUP);
        consumer.setMessageModel(MessageModel.BROADCASTING);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        //订阅Topic,并通过SQL表达式对消息进行过滤
        consumer.subscribe(RocketMQConstants.TOPIC_NAME, MessageSelector.bySql("amount > 5"));
        consumer.registerMessageListener(new SimpleListener());

        consumer.start();
        System.err.println("Consumer 启动");
    }
}
