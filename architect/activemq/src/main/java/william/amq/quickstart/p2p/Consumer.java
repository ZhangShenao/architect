package william.amq.quickstart.p2p;

import org.apache.activemq.ActiveMQConnectionFactory;
import william.amq.common.Constants;
import javax.jms.*;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/6 16:46
 * @Description:基于P2P模式的消息消费者
 */
public class Consumer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory;
        Connection connection = null;
        Session session = null;
        try {
            connectionFactory = new ActiveMQConnectionFactory(Constants.BROKER_URL);
            connection = connectionFactory.createConnection();

            //启动Connection
            connection.start();

            //创建Session会话对象，并指定是否启用事务和消息的确认模式
            //对于事务性会话，在提交事务后会自动确认消息
            //对于非事务性会话，消息的确认模式取决于参数acknowledgeMode
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Destination queue = session.createQueue(Constants.QUEUE_NAME);
            MessageConsumer consumer = session.createConsumer(queue);
            for (int i = 0; i < Constants.MESSAGE_NUM; i++) {
                //同步接收消息，此处会阻塞
                //由于设置了AUTO_ACKNOWLEDGE，且是非事务性会话，因此此处接收到消息后会自动确认
                Message message = consumer.receive();

                //手动确认消息
                //消息确实是会话级别的，即对于同一个Session，只要调用了一次acknowledge()方法，则该Session消费的所有消息都会进行确认
//                message.acknowledge();
                System.err.println("接收到了消息: " + message);
            }
            //对于事务性会话，提交事务会自动签收消息
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
