package william.spring.event;

import org.springframework.context.event.EventListener;

/**
 * @Author zhangshenao
 * @Date 2019-12-26
 * @Description 红包监听器
 */
public class RedPackageListener {
    //注册事件监听器,监听指定事件
    @EventListener(RedPackageEvent.class)
    public void onEvent(RedPackageEvent event) {
        System.err.println(String.format("%s 发了一个红包", event.getName()));
    }
}
