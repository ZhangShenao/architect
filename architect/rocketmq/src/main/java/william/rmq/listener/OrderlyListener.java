package william.rmq.listener;

import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.util.CollectionUtils;
import java.util.List;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/26 20:49
 * @Description:顺序消息监听器
 */
public class OrderlyListener implements MessageListenerOrderly {
    @Override
    public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
        if (CollectionUtils.isEmpty(msgs)) {
            return ConsumeOrderlyStatus.SUCCESS;
        }

        for (MessageExt messageExt : msgs) {
            try {
                String topic = messageExt.getTopic();
                String tags = messageExt.getTags();
                String keys = messageExt.getKeys();
                String payload = new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET);
                System.err.println("消费消息,topic: " + topic + ",tags: " + tags + ",keys: " + keys + ",body: " + payload);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("消费消息失败:" + messageExt);
                return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
            }
        }
        return ConsumeOrderlyStatus.SUCCESS;
    }
}
