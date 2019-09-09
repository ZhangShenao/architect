package william.disruptor.quickstart;

import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/3 15:04
 * @Description:
 */
@Getter
@Setter
public class OrderEvent {
    private long id;
    private long price;
}
