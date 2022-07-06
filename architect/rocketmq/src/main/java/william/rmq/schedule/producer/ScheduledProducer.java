package william.rmq.schedule.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import william.rmq.common.RocketMQConstants;

/**
 * @Author ZhangShenao
 * @Date 2022/7/6 3:08 PM
 * @Desc 延迟消息Producer
 */
public class ScheduledProducer {
    public static void main(String[] args) throws Exception {
        //初始化Producer,指定ProducerGroup
        DefaultMQProducer producer = new DefaultMQProducer(RocketMQConstants.PRODUCER_GROUP);

        //设置NameServer地址
        producer.setNamesrvAddr(RocketMQConstants.NAMESRV_ADDR);

        //启动Producer
        producer.start();

        for (int i = 0; i < 10; i++) {
            //创建消息实例,指定Topic
            String payload = String.format("Scheduled-Message-%d", i + 1);
            Message message = new Message(RocketMQConstants.TOPIC_NAME, payload.getBytes(RemotingHelper.DEFAULT_CHARSET));

            //设置消息延迟级别
            //1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
            message.setDelayTimeLevel(4);

            //同步发送消息,获取发送结果
            SendResult sendResult = producer.send(message);
            System.out.printf("Send Delay Message Result: %s\n", sendResult);
        }

        //关闭Producer
        producer.shutdown();
    }
}
