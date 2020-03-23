package william.designpattern.mediator;

/**
 * @Author zhangshenao
 * @Date 2020-03-23
 * @Description
 */
public class PromotionService implements ServiceProvider {
    @Override
    public String serviceName() {
        return "Promotion-Service";
    }

    @Override
    public void invoke() {
        System.err.println("调用促销服务");
    }
}
