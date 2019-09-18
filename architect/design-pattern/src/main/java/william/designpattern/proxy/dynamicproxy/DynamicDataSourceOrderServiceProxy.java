package william.designpattern.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import william.designpattern.proxy.OrderService;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description 动态代理
 */
public class DynamicDataSourceOrderServiceProxy implements InvocationHandler {
    private OrderService proxied;

    public DynamicDataSourceOrderServiceProxy(OrderService proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.err.println("切换数据源");
        return method.invoke(proxied, args);
    }
}
