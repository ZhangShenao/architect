package william.amq.quickstart.pubsub;

import org.apache.activemq.ActiveMQConnectionFactory;
import william.amq.common.Constants;

import javax.jms.*;
import java.util.Enumeration;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/6 16:26
 * @Description:基于Pub/Sub模式的消息生产者
 */
public class Producer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory;
        Connection connection = null;
        Session session = null;
        try {
            connectionFactory = new ActiveMQConnectionFactory(Constants.BROKER_URL);
            connection = connectionFactory.createConnection();

            session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            //创建Topic
            Destination topic = session.createTopic(Constants.TOPIC_NAME);
            MessageProducer producer = session.createProducer(topic);
            //设置为持久的发送模式
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            connection.start();

            for (int i = 0;i < Constants.MESSAGE_NUM;i++){
                String payload = "Message-" + (i + 1);
                Message message = session.createTextMessage(payload);
                producer.send(message);
                System.err.println("发送了消息: " + payload);
            }

            //提交事务
            session.commit();
        } catch (JMSException e) {
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
