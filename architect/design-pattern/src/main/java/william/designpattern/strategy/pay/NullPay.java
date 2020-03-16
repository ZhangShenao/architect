package william.designpattern.strategy.pay;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description
 */
public class NullPay implements PayStrategy{
    @Override
    public void pay() {
        System.err.println("不支付");
    }
}
