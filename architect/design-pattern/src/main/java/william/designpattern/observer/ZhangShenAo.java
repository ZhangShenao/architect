package william.designpattern.observer;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @Author zhangshenao
 * @Date 2020-03-23
 * @Description
 */
@Component
public class ZhangShenAo implements Fans {
    @Override
    public String name() {
        return "张申傲";
    }

    @Override
    @EventListener(StarEvent.class)
    public void listenStarNews(StarEvent event) {
        System.err.println(name() + ": 了解到了明星的新动态");
        System.err.println(event.desc());
    }


}
