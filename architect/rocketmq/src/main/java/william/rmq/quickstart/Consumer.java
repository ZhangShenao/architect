package william.rmq.quickstart;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import william.rmq.common.RocketMQConstants;
import william.rmq.retry.RetryListener;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/4 09:54
 * @Description:消息消费者,默认采用Push模式
 */
public class Consumer {
    public static void main(String[] args) {
        try {
            //创建DefaultMQPushConsumer
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(RocketMQConstants.CONSUMER_GROUP);

            //设置NameServer地址
            consumer.setNamesrvAddr(RocketMQConstants.NAMESRV_ADDR);

            //设置消费模式:集群消费/广播消费,默认是集群消费模式
            consumer.setMessageModel(MessageModel.CLUSTERING);

            //设置消费者启动时,初始的消费位置(从Queue头/尾开始消费)。Consumer启动后,offset会随着对消息的消费逐渐前进
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

            //订阅Topic,并且可以根据Tag对消息进行过滤
            consumer.subscribe(RocketMQConstants.TOPIC_NAME, "*");

            //注册消息监听器
            consumer.registerMessageListener(new RetryListener());

            //启动Consumer
            consumer.start();
            System.err.println("Consumer Start");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
