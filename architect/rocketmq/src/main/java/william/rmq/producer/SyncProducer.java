package william.rmq.producer;

import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import william.rmq.common.RocketMQConstants;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/4 09:53
 * @Description:同步发送的消息生产者
 */
public class SyncProducer {
    private static final int MESSAGE_NUM = 8;

    public static void main(String[] args) {
        //创建DefaultMQProducer
        DefaultMQProducer producer = new DefaultMQProducer(RocketMQConstants.PRODUCER_GROUP);

        try {
            //设置NameServer地址
            producer.setNamesrvAddr(RocketMQConstants.NAMESRV_ADDR);

            //设置消息发送超时时间
            producer.setSendMsgTimeout(RocketMQConstants.SNED_TIME_OUT_MILLIS);

            //设置消息发送失败时的最大重试次数
            producer.setRetryTimesWhenSendFailed(RocketMQConstants.RETRY_TIMES_WHEN_SEND_FAILED);
            producer.setRetryTimesWhenSendAsyncFailed(RocketMQConstants.RETRY_TIMES_WHEN_SEND_FAILED);

            //启动Producer
            producer.start();

            for (int i = 1; i <= MESSAGE_NUM; i++) {
                String tag = (i % 2 == 0) ? "TagA" : "TagB";
                String keys = "keys" + i;
                String payload = "Test-Message-" + i;
                /**
                 * 创建消息
                 * topic:消息发送到的Topic名称,必须指定
                 * tag:消息标签,主要用于过滤
                 * keys:用户自定义的key，一般在业务上保证唯一性
                 * body:消息体内容,定义为字节数组形式
                 */
                Message message = new Message(RocketMQConstants.TOPIC_NAME, tag, keys, payload.getBytes(RemotingHelper.DEFAULT_CHARSET));

                //同步发送消息,获取Broker的ACK发送结果
                SendResult sendResult = producer.send(message);
                System.err.println("Send Message Result: " + sendResult);

                //消息发送异常，考虑重试
                SendStatus sendStatus = sendResult.getSendStatus();
                if (sendStatus == null || SendStatus.SEND_OK != sendStatus) {
                    System.err.println("Message SendStatus No OK!! messageId: " + sendResult.getMsgId());
                    //TODO 重试
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭Producer
            producer.shutdown();
        }

    }
}