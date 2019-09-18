package william.designpattern.proxy;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description 被代理类,实现目标接口
 */
public class OrderServiceImpl implements OrderService {
    @Override
    public void createOrder(Order order) {
        System.err.println("创建订单: " + order);
    }
}
