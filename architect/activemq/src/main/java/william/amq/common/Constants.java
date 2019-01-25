package william.amq.common;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/6 16:26
 * @Description:
 */
public interface Constants {
    String BROKER_URL = "nio://localhost:61617";
    String QUEUE_NAME = "Test-Queue";
    String TOPIC_NAME = "Test-Topic";
    String CONSUMER_CLIENT_ID = "Client-1";
    String DURABLE_SUBSCRIBER_NAME = "Durable-Subscriber";
    int MESSAGE_NUM = 5;
}
