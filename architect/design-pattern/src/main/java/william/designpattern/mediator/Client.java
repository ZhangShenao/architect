package william.designpattern.mediator;

/**
 * @Author zhangshenao
 * @Date 2020-03-23
 * @Description 中介者模式:用一个中介者对象,封装一系列对象之间的交互,将对象之间交互的网状结构,转换成依赖于中介者的星状结构,从而有效减少类之间的耦合
 */
public class Client {
    public static void main(String[] args) {
        ServiceRegistry registry = new ServiceRegistry();
        registry.registerService(new OrderService());
        registry.registerService(new ProductService());
        registry.registerService(new PromotionService());

        ServiceConsumer consumer = new ServiceConsumer(registry);
        consumer.invoke("Order-Service");
    }
}
