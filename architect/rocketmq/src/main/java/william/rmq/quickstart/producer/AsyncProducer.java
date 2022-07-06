package william.rmq.quickstart.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import william.rmq.common.RocketMQConstants;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author ZhangShenao
 * @Date 2022/7/6 2:30 PM
 * @Desc 异步Producer
 */
public class AsyncProducer {
    public static void main(String[] args) throws Exception {
        //初始化Producer,指定ProducerGroup
        DefaultMQProducer producer = new DefaultMQProducer(RocketMQConstants.PRODUCER_GROUP);

        //设置NameServer地址
        producer.setNamesrvAddr(RocketMQConstants.NAMESRV_ADDR);

        //启动Producer
        producer.start();

        //设置异步发送失败重试次数
        producer.setRetryTimesWhenSendAsyncFailed(3);

        int messageCount = 10;
        CountDownLatch latch = new CountDownLatch(messageCount);

        for (int i = 0; i < messageCount; i++) {
            //构造消息
            String payload = String.format("Async-Message-%d", (i + 1));
            Message message = new Message(RocketMQConstants.TOPIC_NAME, payload.getBytes(RemotingHelper.DEFAULT_CHARSET));

            //异步发送
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {  //发送成功
                    latch.countDown();
                    System.out.printf("Send Message Success. Result: %s\n", sendResult);
                }

                @Override
                public void onException(Throwable e) {  //发送失败
                    latch.countDown();
                    System.out.println("Send Message Fail: " + e.getMessage());
                }
            });
        }

        //等待消息发送完成
        latch.await(10L, TimeUnit.SECONDS);

        //关闭Producer
        producer.shutdown();
    }
}
