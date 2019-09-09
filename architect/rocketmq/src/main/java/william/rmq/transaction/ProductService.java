package william.rmq.transaction;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import william.rmq.common.RocketMQConstants;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/27 17:09
 * @Description:使用RocketMQ事务消息——商品服务接收下单的事务消息,如果消息成功commit则本地减库存
 */
public class ProductService {
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        consumer.setNamesrvAddr(RocketMQConstants.NAMESRV_ADDR);
        consumer.setConsumerGroup(RocketMQConstants.TRANSACTION_CONSUMER_GROUP);
        consumer.subscribe(RocketMQConstants.TRANSACTION_TOPIC_NAME, "*");
        consumer.registerMessageListener(new ProductListener());
        consumer.start();
        System.err.println("ProductService Start");
    }
}
