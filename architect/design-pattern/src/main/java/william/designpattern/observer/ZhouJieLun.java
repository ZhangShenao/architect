package william.designpattern.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Author zhangshenao
 * @Date 2020-03-23
 * @Description
 */
@Component
public class ZhouJieLun implements Star {
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public String name() {
        return "周杰伦";
    }

    @Override
    public void action() {
        System.err.println(name() + ": 有新动态啦");
        applicationContext.publishEvent(new PublishAlbumEvent());
        applicationContext.publishEvent(new PublishSingleSongEvent());
    }
}
