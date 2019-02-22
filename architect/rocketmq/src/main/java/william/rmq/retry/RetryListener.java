package william.rmq.retry;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.util.CollectionUtils;
import java.util.List;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/4 11:33
 * @Description:模拟消费失败后的Broker自动重试 注:自动重试仅对集群消费模式有效
 */
public class RetryListener implements MessageListenerConcurrently {
    private static final int MAX_RECONSUME_TIMES = 3;       //消费失败后，消息最大重试次数

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        if (CollectionUtils.isEmpty(msgs)) {
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }

        MessageExt messageExt = msgs.get(0);
        try {
            //逐条消费
            String topic = messageExt.getTopic();
            String tags = messageExt.getTags();
            String keys = messageExt.getKeys();
            String payload = new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET);
            if ("keys2".equalsIgnoreCase(keys)) {
                throw new RuntimeException("模拟消息消费异常");
            }

            System.err.println("消费消息,topic: " + topic + ",tags: " + tags + ",keys: " + keys + ",body: " + payload);
            //消费成功,返回ConsumeConcurrentlyStatus.CONSUME_SUCCESS
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("消费消息失败: " + messageExt);

            //返回ConsumeConcurrentlyStatus.RECONSUME_LATER后,Broker会自动进行消息重试,并记录重试次数。
            //超过最大重试次数后,记录日志,并返回消费成功
            int reconsumeTimes = messageExt.getReconsumeTimes();
            System.err.println("当前消息重试次数:" + reconsumeTimes);
            if (reconsumeTimes >= MAX_RECONSUME_TIMES) {
                System.err.println("已达最大重试次数,记录日志");
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }

    }
}
