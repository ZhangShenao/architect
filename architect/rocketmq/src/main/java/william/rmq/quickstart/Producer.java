package william.rmq.quickstart;

import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import william.rmq.common.RocketMQConstants;

import java.util.List;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/4 09:53
 * @Description:消息生产者
 */
public class Producer {
    public static void main(String[] args) throws Exception {
        //创建DefaultMQProducer
        DefaultMQProducer producer = new DefaultMQProducer(RocketMQConstants.PRODUCER_GROUP);

        //设置NameServer地址
        producer.setNamesrvAddr(RocketMQConstants.NAMESRV_ADDR);

        //设置消息发送超时时间
        producer.setSendMsgTimeout(RocketMQConstants.SNED_TIME_OUT_MILLIS);

        //设置消息发送失败时的最大重试次数
        producer.setRetryTimesWhenSendFailed(RocketMQConstants.RETRY_TIMES_WHEN_SEND_FAILED);
        producer.setRetryTimesWhenSendAsyncFailed(RocketMQConstants.RETRY_TIMES_WHEN_SEND_FAILED);

        //启动Producer
        producer.start();

        Message message = null;
        for (int i = 1;i <= 8;i++){
            String tag = (i % 2 == 0) ? "TagA" : "TagB";
            //创建消息
            message = new Message(RocketMQConstants.TOPIC_NAME,	    //主题
                    tag,                    //标签，可用于过滤消息
                    "key" + i,	        //用户自定义的key，一般在业务上保证唯一性
                    ("Test-Message-" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));	 //消息内容实体
            /*if (i % 2 == 0){
                //设置消息的延迟级别
                message.setDelayTimeLevel(3);
            }*/

            //同步发送消息，获取Broker的ACK发送结果
            SendResult sendResult = producer.send(message);
            //将消息发往指定的Message Queue
            /*SendResult sendResult  = producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    Integer queueId = (Integer)arg;
                    return mqs.get(queueId);
                }
            },2);*/
            System.err.println("Message: " + message + ", Send Result: " + sendResult);


            //消息发送异常，考虑重试
            SendStatus sendStatus = sendResult.getSendStatus();
            if (sendStatus == null || SendStatus.SEND_OK != sendStatus){
                System.err.println("Message SendStatus No OK!! messageId: " + sendResult.getMsgId());
                //重试
            }

            //异步发送消息，实现Callback
            /*producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.err.println("Send Message Success!! messageId: " + sendResult.getMsgId() + ", status: " + sendResult.getSendStatus());
                }

                @Override
                public void onException(Throwable e) {
                    System.err.println("Send Message Error : " + e.getMessage());
                }
            });*/
        }

        //关闭Producer
//        producer.shutdown();
    }
}
