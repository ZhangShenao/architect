package william.designpattern.proxy;

import java.lang.reflect.Proxy;

import org.junit.Test;

import william.designpattern.proxy.dynamicproxy.DynamicDataSourceOrderServiceProxy;
import william.designpattern.proxy.staticproxy.DynamicDataSourceOrderServiceImpl;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description 代理模式:为目标对象提供一种代理,以控制对目标对象的访问,或对目标对象进行功能的增强。代理对象在客服端和目标对象之间起到中介作用。
 * 代理模式属于结构型模式
 */
public class TestProxy {
    @Test
    public void testStaticProxy() {
        OrderService proxied = new OrderServiceImpl();
        OrderService proxy = new DynamicDataSourceOrderServiceImpl(proxied);
        Order order = new Order();
        order.setId(1L);
        order.setInfo("商品订单");
        order.setCreateTime(System.currentTimeMillis());
        proxy.createOrder(order);
    }

    @Test
    public void testDynamicProxy() {
        OrderService proxied = new OrderServiceImpl();
        OrderService proxy =
                (OrderService) Proxy
                        .newProxyInstance(OrderService.class.getClassLoader(), proxied.getClass().getInterfaces(),
                                new DynamicDataSourceOrderServiceProxy(proxied));
        Order order = new Order();
        order.setId(1L);
        order.setInfo("商品订单");
        order.setCreateTime(System.currentTimeMillis());
        proxy.createOrder(order);
    }
}
