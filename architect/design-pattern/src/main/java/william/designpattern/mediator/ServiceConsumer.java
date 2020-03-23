package william.designpattern.mediator;

/**
 * @Author zhangshenao
 * @Date 2020-03-23
 * @Description 服务消费方
 */
public class ServiceConsumer {
    //ServiceConsumer仅需直接与ServiceRegistry交互即可,无需关心ServiceProvider内部逻辑
    private ServiceRegistry registry;


    public ServiceConsumer(ServiceRegistry registry) {
        this.registry = registry;
    }

    public void invoke(String serviceName) {
        registry.invoke(serviceName);
    }
}
