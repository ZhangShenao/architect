package william.rmq.order.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import william.rmq.common.RocketMQConstants;

import java.util.Objects;
import java.util.UUID;

import static william.rmq.common.RocketMQConstants.ORDER_STATUS;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/26 20:31
 * @Description:顺序消息生产端 要想保证消息的完全有序, 必须实现Producer端将消息发往同一个MessageQueue, 且Consumer端从同一个MessageQueue中消费
 * 以订单系统为例:同一条订单(同一个订单id)的不同消息发往同一个MessageQueue,而不同订单的消息可以完全并发发送
 * 注:1.广播消费模式无法实现顺序消息
 * 2.异步发送无法实现顺序消息
 */
public class OrderlyProducer {
    private static final int ORDER_NUM = 10;

    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer(RocketMQConstants.PRODUCER_GROUP);

        try {
            producer.setNamesrvAddr(RocketMQConstants.NAMESRV_ADDR);
            producer.setSendMsgTimeout(RocketMQConstants.SNED_TIME_OUT_MILLIS);
            producer.setRetryTimesWhenSendFailed(RocketMQConstants.RETRY_TIMES_WHEN_SEND_FAILED);
            producer.setRetryTimesWhenSendAsyncFailed(RocketMQConstants.RETRY_TIMES_WHEN_SEND_FAILED);
            producer.start();

            for (int i = 0; i < ORDER_NUM; i++) {
                //将同一个订单的消息发往同一个MessageQueue,保证订单状态的顺序
                //对于不同订单的消息,可以不关心顺序
                String orderId = UUID.randomUUID().toString();
                for (int j = 0, len = ORDER_STATUS.length; j < len; j++) {
                    String orderStatus = ORDER_STATUS[j];
                    String payload = "[订单消息:orderId = " + orderId + ", status = " + orderStatus + "]";
                    Message message = new Message(RocketMQConstants.TOPIC_NAME, orderStatus, orderId, payload.getBytes(RemotingHelper.DEFAULT_CHARSET));
                    SendResult sendResult = producer.send(message, (mqs, msg, arg) -> {
                        String id = (String) arg;
                        int index = Math.abs(Objects.hash(id)) % mqs.size();
                        return mqs.get(index);
                    }, orderId);
                    System.out.println("Send Message Result: " + sendResult);
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
