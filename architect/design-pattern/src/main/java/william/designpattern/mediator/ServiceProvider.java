package william.designpattern.mediator;

/**
 * @Author zhangshenao
 * @Date 2020-03-23
 * @Description 服务提供方
 */
public interface ServiceProvider {
    String serviceName();

    void invoke();
}
