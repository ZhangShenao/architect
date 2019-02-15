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

    String JMS_TOPIC_TEMPLATE = "jmsTopicTemplate";
    String JMS_QUEUE_TEMPLATE = "jmsQueueTemplate";
    String QUEUE_LISTENER_CONTAINER_FACTORY = "queueListenerContainerFactory";
    String TOPIC_LISTENER_CONTAINER_FACTORY = "topicListenerContainerFactory";

    interface QueueNames{
        String REQUEST_QUEUE = "Reply-Queue";
        String RESPONSE_QUEUE = "Response-Queue";
    }
}
