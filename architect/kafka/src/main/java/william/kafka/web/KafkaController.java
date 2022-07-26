package william.kafka.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import william.kafka.producer.SpringBootKafkaProducer;

/**
 * @Auther: ZhangShenao
 * @Date 2022/7/26 1:42 PM
 * @Description: Kafka API
 */
@RestController
@RequestMapping("/kafka")
public class KafkaController {
    @Autowired
    private SpringBootKafkaProducer producer;

    @GetMapping("/send")
    public void sendMessage(@RequestParam("msg") String msg) {
        producer.sendMsg(msg);
    }

}
