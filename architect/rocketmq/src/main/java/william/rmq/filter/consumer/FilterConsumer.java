package william.rmq.filter.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import william.rmq.common.RocketMQConstants;

/**
 * @Author ZhangShenao
 * @Date 2022/7/7 1:34 PM
 * @Desc 消息过滤Consumer
 */
public class FilterConsumer {
    public static void main(String[] args) throws Exception {
        //初始化Consumer,指定ConsumerGroup
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(RocketMQConstants.CONSUMER_GROUP);

        //设置NameServer地址
        consumer.setNamesrvAddr(RocketMQConstants.NAMESRV_ADDR);

        //订阅主题,基于Tag进行消息过滤
        consumer.subscribe(RocketMQConstants.TOPIC_NAME, "A");

        //注册消息回调
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);

            //消费成功ACK
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        //启动Consumer
        consumer.start();

        System.out.println("Consumer Started");
    }
}
