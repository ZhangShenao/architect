package william.rmq.quickstart.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import william.rmq.common.RocketMQConstants;

import java.util.List;

/**
 * @Author ZhangShenao
 * @Date 2022/7/6 2:13 PM
 * @Desc 消费者Consumer
 */
public class Consumer {
    public static void main(String[] args) throws Exception {
        //初始化Consumer,指定ConsumerGroup
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(RocketMQConstants.CONSUMER_GROUP);

        //设置NameServer地址
        consumer.setNamesrvAddr(RocketMQConstants.NAMESRV_ADDR);

        //订阅主题
        consumer.subscribe(RocketMQConstants.TOPIC_NAME, "*");   //订阅所有tag

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
