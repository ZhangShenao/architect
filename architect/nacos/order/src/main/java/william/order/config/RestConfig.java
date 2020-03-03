package william.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

/**
 * @Author zhangshenao
 * @Date 2020-02-10
 * @Description
 */
@Configuration
public class RestConfig {
    @Bean
    @LoadBalanced   //使用Ribbon负载均衡机制
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    //指定Ribbon负载均衡策略
    @Bean
    public IRule lbRule() {
        return new RandomRule();
    }
}
