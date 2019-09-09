package william.rmq.listener;

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
 * @Description:简单消息监听器
 */
public class SimpleListener implements MessageListenerConcurrently {
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

            System.err.println("消费消息,topic: " + topic + ",tags: " + tags + ",keys: " + keys + ",body: " + payload);
            //消费成功,返回ConsumeConcurrentlyStatus.CONSUME_SUCCESS
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("消费消息失败:" + messageExt);
            //消费消息异常,返回ConsumeConcurrentlyStatus.RECONSUME_LATER
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }

    }
}
