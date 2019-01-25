package william.amq.quickstart.p2p;

import org.apache.activemq.ActiveMQConnectionFactory;
import william.amq.common.Constants;
import javax.jms.*;
import java.util.Enumeration;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/6 16:26
 * @Description:基于P2P模式的消息生产者
 */
public class Producer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory;
        Connection connection = null;
        Session session = null;
        try {
            connectionFactory = new ActiveMQConnectionFactory(Constants.BROKER_URL);
            connection = connectionFactory.createConnection();
            connection.start();

            //创建事务性会话
            session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            Destination queue = session.createQueue(Constants.QUEUE_NAME);
            MessageProducer producer = session.createProducer(queue);
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
