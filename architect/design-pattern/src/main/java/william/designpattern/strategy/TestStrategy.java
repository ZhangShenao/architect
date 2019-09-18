package william.designpattern.strategy;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description 策略模式:定义了算法家族、分别封装起来,让它们之间可以互相替换。此模式让算法的变化不会影响到使用算法的用户。
 * 适用场景:实现一个算法有多种不同的策略,客户端需要从中选择一种
 */
public class TestStrategy {
    public static void main(String[] args) throws Exception {
        PayStrategy payStrategy = PayType.WECHAT_PAY.getStrategyClazz().newInstance();
        payStrategy.pay();
    }
}
