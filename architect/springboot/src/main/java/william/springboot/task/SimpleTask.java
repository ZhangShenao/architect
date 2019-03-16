package william.springboot.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/10 19:04
 * @Description:
 */
@Component
public class SimpleTask {
//    @Scheduled(initialDelay = 1000L, fixedDelay = 5000L)
    public void deSimpleTask() {
        System.err.println("Do Simple Task");
    }
}
