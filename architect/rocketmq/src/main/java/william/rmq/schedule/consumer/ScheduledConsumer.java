package william.rmq.schedule.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import william.rmq.common.RocketMQConstants;

/**
 * @Author ZhangShenao
 * @Date 2022/7/6 3:13 PM
 * @Desc 延迟消息Consumer
 */
public class ScheduledConsumer {
    public static void main(String[] args) throws Exception {
        //初始化Consumer,指定ConsumerGroup
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(RocketMQConstants.CONSUMER_GROUP);

        //设置NameServer地址
        consumer.setNamesrvAddr(RocketMQConstants.NAMESRV_ADDR);

        //订阅主题
        consumer.subscribe(RocketMQConstants.TOPIC_NAME, "*");   //订阅所有tag

        //注册消息回调
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            msgs.forEach(x -> {
                //打印消息延迟时间
                System.out.println("Receive message[msgId=" + x.getMsgId() + "] "
                        + (System.currentTimeMillis() - x.getStoreTimestamp()) + "ms later");
            });

            //消费成功ACK
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        //启动Consumer
        consumer.start();

        System.out.println("Consumer Started");
    }
}
