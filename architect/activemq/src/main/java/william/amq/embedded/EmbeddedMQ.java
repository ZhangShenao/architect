package william.amq.embedded;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.jmx.ManagementContext;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/13 08:25
 * @Description:嵌入式MQ
 */
public class EmbeddedMQ {
    public static void main(String[] args) throws Exception {
        //创建BrokerService实例
        BrokerService broker = new BrokerService();

        //设置Broker名称
        broker.setBrokerName("EmbeddedMQ");

        //设置监听地址
        broker.addConnector("tcp://localhost:61618");

        //设置ManagementContext
        broker.setManagementContext(new ManagementContext());

        //启动BrokerService
        broker.start();
    }
}
