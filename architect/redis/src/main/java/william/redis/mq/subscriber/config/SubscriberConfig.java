package william.redis.mq.subscriber.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import william.redis.consts.Constants;
import william.redis.mq.subscriber.listener.MessageListener;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/6 16:05
 * @Description:
 */
@Configuration
public class SubscriberConfig {
    @Bean
    public MessageListenerAdapter messageListenerAdapter(MessageListener listener) {
        return new MessageListenerAdapter(listener, "onMessage");
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory,
                                                                       MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic(Constants.MESSAGE_TOPIC));
        return container;
    }
}
