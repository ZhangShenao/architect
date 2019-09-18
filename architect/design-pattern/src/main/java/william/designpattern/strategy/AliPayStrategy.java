package william.designpattern.strategy;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description
 */
public class AliPayStrategy implements PayStrategy {
    @Override
    public void pay() {
        System.err.println("使用支付宝支付");
    }
}
