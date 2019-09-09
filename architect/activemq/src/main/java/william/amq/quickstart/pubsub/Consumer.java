package william.amq.quickstart.pubsub;

import org.apache.activemq.ActiveMQConnectionFactory;
import william.amq.common.Constants;
import william.amq.quickstart.listener.CommonListener;

import javax.jms.*;
import javax.management.JMException;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/6 16:46
 * @Description:基于Pub/Sub模式的消息消费者,并且进行持久化订阅
 * 对于非持久化订阅，消费者必须先注册订阅信息，并且处于在线状态，才能成功消费消息；
 * 对于持久化订阅，消费者需要先注册一次订阅信息，后面无论是否处于在线状态，都可以接收到消息
 */
public class Consumer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory;
        Connection connection = null;
        Session session = null;
        try {
            connectionFactory = new ActiveMQConnectionFactory(Constants.BROKER_URL);
            connection = connectionFactory.createConnection();
            //设置消费者的ClientId，以注册持久订阅
            connection.setClientID(Constants.CONSUMER_CLIENT_ID);

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);


            //使用TopicSubscriber创建持久订阅
            Topic topic = session.createTopic(Constants.TOPIC_NAME);
            TopicSubscriber subscriber = session.createDurableSubscriber(topic, Constants.DURABLE_SUBSCRIBER_NAME);

            //设置完毕，启动Connection
            connection.start();

            //设置消息监听器，异步接收消息
            subscriber.setMessageListener(new CommonListener());

            Thread.sleep(Long.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                session.close();
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
