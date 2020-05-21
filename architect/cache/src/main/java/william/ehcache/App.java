package william.ehcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import william.ehcache.service.EhCacheService;

/**
 * @Author zhangshenao
 * @Date 2020-05-21
 * @Description
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(App.class);
        EhCacheService service = applicationContext.getBean(EhCacheService.class);
        service.print();
    }
}
