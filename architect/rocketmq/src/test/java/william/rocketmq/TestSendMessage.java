package william.rocketmq;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import william.rmq.common.RocketMQConstants;
import william.rmq.springboot.RocketMQApplication;
import william.rmq.springboot.producer.RocketMQProducer;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/26 13:43
 * @Description:
 */
@SpringBootTest(classes = RocketMQApplication.class)
@RunWith(SpringRunner.class)
public class TestSendMessage {
    @Autowired
    private RocketMQProducer producer;

    @Test
    public void testSendMessageSync() throws Exception {
        for (int i = 1; i <= 10; i++) {
            String tag = (i % 2 == 0) ? "TagA" : "TagB";
            String keys = "keys" + i;
            String payload = "Test-Message-" + i;
            Message message = new Message(RocketMQConstants.TOPIC_NAME, tag, keys, payload.getBytes(RemotingHelper.DEFAULT_CHARSET));

            //同步发送消息,获取Broker的ACK发送结果
            SendResult sendResult = producer.sendMessageSync(message);
            System.err.println("Send Message Result: " + sendResult);
        }
    }
}
