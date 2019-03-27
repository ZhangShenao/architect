package william.rmq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import william.rmq.common.RocketMQConstants;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/27 14:25
 * @Description:通过SQL表达式对Message进行过滤——Producer端
 */
public class SQLFilterProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setNamesrvAddr(RocketMQConstants.NAMESRV_ADDR);
        producer.setProducerGroup(RocketMQConstants.PRODUCER_GROUP);
        producer.start();
        System.err.println("Producer启动");

        for (int i = 1; i <= 10; i++) {
            String tag = "Tag";
            String keys = "keys" + i;
            String payload = "Test-Message-" + i;
            Message message = new Message(RocketMQConstants.TOPIC_NAME, tag, keys, payload.getBytes(RemotingHelper.DEFAULT_CHARSET));

            //自定义Message属性,可以通过SQL表达式进行过滤
            message.putUserProperty("amount",String.valueOf(i));

            SendResult sendResult = producer.send(message);
            System.err.println("Send Message Result: " + sendResult);
        }
        producer.shutdown();
    }
}
