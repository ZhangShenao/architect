package william.rmq.transaction;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/27 17:14
 * @Description:
 */
public class ProductListener implements MessageListenerConcurrently {
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        Optional.ofNullable(msgs).orElse(Collections.emptyList()).forEach(m -> {
            String orderId = m.getKeys();
            System.err.println("监听到下单消息,orderId: " + orderId + ", 商品服务减库存");
        });
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
