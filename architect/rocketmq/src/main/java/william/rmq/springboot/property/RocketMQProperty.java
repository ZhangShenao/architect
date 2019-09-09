package william.rmq.springboot.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/26 13:17
 * @Description:
 */
@ConfigurationProperties(prefix = "rocketmq")
@Component
@Getter
@Setter
public class RocketMQProperty {
    private String namesrvAddr;
    private String producerGroup;
    private String consumerGroup;
    private String topic;
}
