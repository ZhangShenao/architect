package william.designpattern.strategy.pay;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description
 */
public class UnionPayStrategy implements PayStrategy {
    @Override
    public void pay() {
        System.err.println("使用银联支付");
    }
}
