package william.rmq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import william.rmq.common.RocketMQConstants;
import william.rmq.listener.SimpleListener;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/27 14:13
 * @Description:使用tag标签对消息记性过滤——Producer端
 */
public class TagFilterConsumer {
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        consumer.setNamesrvAddr(RocketMQConstants.NAMESRV_ADDR);
        consumer.setConsumerGroup(RocketMQConstants.CONSUMER_GROUP);
        consumer.setMessageModel(MessageModel.BROADCASTING);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        //订阅Topic,并通过Tag对消息进行过滤
        consumer.subscribe(RocketMQConstants.TOPIC_NAME, "TagA");
        consumer.registerMessageListener(new SimpleListener());

        consumer.start();
        System.err.println("Consumer 启动");
    }
}
