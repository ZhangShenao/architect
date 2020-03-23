package william.designpattern.mediator;

/**
 * @Author zhangshenao
 * @Date 2020-03-23
 * @Description
 */
public class ProductService implements ServiceProvider {
    @Override
    public String serviceName() {
        return "Product-Service";
    }

    @Override
    public void invoke() {
        System.err.println("调用商品服务");
    }
}
