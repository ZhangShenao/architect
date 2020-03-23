package william.designpattern.mediator;

/**
 * @Author zhangshenao
 * @Date 2020-03-23
 * @Description
 */
public class OrderService implements ServiceProvider {
    @Override
    public String serviceName() {
        return "Order-Service";
    }

    @Override
    public void invoke() {
        System.err.println("调用订单服务");
    }
}
