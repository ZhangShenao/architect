package william.designpattern.mediator;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhangshenao
 * @Date 2020-03-23
 * @Description 服务注册中心:服务消费方和服务提供方无需直接交互,所有的服务调用请求都统一由注册中心处理
 */
public class ServiceRegistry {
    private Map<String, ServiceProvider> registry = new HashMap<>();

    //服务注册
    public void registerService(ServiceProvider service) {
        registry.put(service.serviceName(), service);
    }

    public void invoke(String serviceName) {
        registry.computeIfPresent(serviceName, (s, serviceProvider) -> {
            serviceProvider.invoke();
            return null;
        });
    }
}
