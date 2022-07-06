package william.rmq.order.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import william.rmq.common.RocketMQConstants;


/**
 * @Author ZhangShenao
 * @Date 2022/7/6 2:51 PM
 * @Desc 顺序Consumer
 */
public class OrderlyConsumer {
    public static void main(String[] args) throws Exception {
        //初始化Consumer,指定ConsumerGroup
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(RocketMQConstants.CONSUMER_GROUP);

        //设置NameServer地址
        consumer.setNamesrvAddr(RocketMQConstants.NAMESRV_ADDR);

        //订阅主题
        consumer.subscribe(RocketMQConstants.TOPIC_NAME, "创建订单 || 支付订单 || 关闭订单");

        //注册顺序消息回调
        consumer.registerMessageListener((MessageListenerOrderly) (msgs, context) -> {
            context.setAutoCommit(false);   //禁用Consumer自动ACK
            System.out.printf(Thread.currentThread().getName() + " Receive New Messages: " + msgs + "%n");

            //手动ACK
            return ConsumeOrderlyStatus.SUCCESS;
        });

        //启动Consumer
        consumer.start();
        System.out.println("Consumer Started");
    }
}
