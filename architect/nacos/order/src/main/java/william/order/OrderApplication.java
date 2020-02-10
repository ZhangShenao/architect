package william.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author zhangshenao
 * @Date 2020-02-10
 * @Description
 */
@SpringBootApplication
@EnableDiscoveryClient  //该注解也可以不加,SpringBoot会自动装配
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
