package william.guavacache;

import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import william.guavacache.service.GuavaCacheService;

/**
 * @Author zhangshenao
 * @Date 2020-05-21
 * @Description
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(App.class);
        GuavaCacheService service = applicationContext.getBean(GuavaCacheService.class);

        //        service.put("K1", "V1");
        service.put("K2", "V2");
        service.put("K3", "V3");

        Optional<String> value = service.get("K1");
        value.ifPresent(System.err::println);
        service.remove("K1");

        service.stat();
        service.clear();

    }
}
