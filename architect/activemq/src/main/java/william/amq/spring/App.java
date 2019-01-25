package william.amq.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import william.amq.spring.constant.MQConstants;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/10 10:50
 * @Description:
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(App.class);
        JmsTemplate queueJmsTemplate = (JmsTemplate) applicationContext.getBean("jmsQueueTemplate");
        queueJmsTemplate.convertAndSend(MQConstants.SAMPLE_QUEUE_NAME,"payload");
        JmsTemplate topicJmsTemplate = (JmsTemplate) applicationContext.getBean("jmsTopicTemplate");
        topicJmsTemplate.convertAndSend(MQConstants.SAMPLE_TOPIC_NAME,"payload");
        System.err.println("Message Send Success");
    }
}
