package william.rmq.quickstart.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import william.rmq.common.RocketMQConstants;


/**
 * @Author ZhangShenao
 * @Date 2022/7/6 2:06 PM
 * @Desc 同步Producer
 */
public class SyncProducer {
    public static void main(String[] args) throws Exception {
        //初始化Producer,指定ProducerGroup
        DefaultMQProducer producer = new DefaultMQProducer(RocketMQConstants.PRODUCER_GROUP);

        //设置NameServer地址
        producer.setNamesrvAddr(RocketMQConstants.NAMESRV_ADDR);

        //启动Producer
        producer.start();

        for (int i = 0; i < 10; i++) {
            //创建消息实例,指定Topic
            String payload = String.format("Test-Message-%d", i + 1);
            Message message = new Message(RocketMQConstants.TOPIC_NAME, payload.getBytes(RemotingHelper.DEFAULT_CHARSET));

            //同步发送消息,获取发送结果
            SendResult sendResult = producer.send(message);
            System.out.printf("Send Result: %s\n", sendResult);
        }

        //关闭Producer
        producer.shutdown();
    }
}
