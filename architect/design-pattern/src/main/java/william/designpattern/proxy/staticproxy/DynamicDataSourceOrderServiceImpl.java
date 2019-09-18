package william.designpattern.proxy.staticproxy;

import william.designpattern.proxy.Order;
import william.designpattern.proxy.OrderService;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description 静态代理类——实现目标接口,增强了动态切换数据源的方法
 */
public class DynamicDataSourceOrderServiceImpl implements OrderService {
    private OrderService proxied;

    public DynamicDataSourceOrderServiceImpl(OrderService proxied) {
        this.proxied = proxied;
    }


    @Override
    public void createOrder(Order order) {
        System.err.println("切换数据源");
        proxied.createOrder(order);
    }
}
