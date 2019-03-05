package william.disruptor.quickstart;

import com.lmax.disruptor.EventFactory;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/3 15:08
 * @Description:
 */
public class OrderEventFactory implements EventFactory<OrderEvent> {
    @Override
    public OrderEvent newInstance() {
        return new OrderEvent();
    }
}
