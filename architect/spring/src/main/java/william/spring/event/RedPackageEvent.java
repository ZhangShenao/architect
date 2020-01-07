package william.spring.event;

import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2019-12-26
 * @Description
 */
@Data
public class RedPackageEvent {
    private String name;

    public static RedPackageEvent valueOf(String name) {
        RedPackageEvent e = new RedPackageEvent();
        e.name = name;
        return e;
    }
}
