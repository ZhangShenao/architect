package william.rmq.common;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/12/6 23:42
 * @Description:
 */
public interface RocketMQConstants {
    String TOPIC_NAME = "test_topic";
    String NAMESRV_ADDR = "localhost:9876";

    String PRODUCER_GROUP = "test_producer_group";
    String CONSUMER_GROUP = "test_consumer_group";
    String PULL_CONSUMER_GROUP = "Test-Pull-Consumer-Group";

    int RETRY_TIMES_WHEN_SEND_FAILED = 5;

    int SNED_TIME_OUT_MILLIS = 5 * 1000;

    String TRANSACTION_TOPIC_NAME = "TX-Topic";
    String TRANSACTION_PRODUCER_GROUP = "TX-Producer-Group";
    String TRANSACTION_CONSUMER_GROUP = "TX-Consumer-Group";

    String[] ORDER_STATUS = {"创建订单", "支付订单", "关闭订单"};
}
