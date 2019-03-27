package william.rmq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import william.rmq.common.RocketMQConstants;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/27 14:09
 * @Description:使用tag标签对消息记性过滤——Producer端
 */
public class TagFilterProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setNamesrvAddr(RocketMQConstants.NAMESRV_ADDR);
        producer.setProducerGroup(RocketMQConstants.PRODUCER_GROUP);
        producer.start();
        System.err.println("Producer启动");

        for (int i = 1; i <= 10; i++) {
            //设置消息Tag,是消息的二级标签,主要用于过滤
            String tag = (i % 2 == 0) ? "TagA" : "TagB";
            String keys = "keys" + i;
            String payload = "Test-Message-" + i;
            Message message = new Message(RocketMQConstants.TOPIC_NAME, tag, keys, payload.getBytes(RemotingHelper.DEFAULT_CHARSET));

            SendResult sendResult = producer.send(message);
            System.err.println("Send Message Result: " + sendResult);
        }
        producer.shutdown();
    }
}
