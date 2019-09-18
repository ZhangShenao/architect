package william.designpattern.strategy;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description 支付方式枚举
 */
public enum PayType {
    ALI_PAY("支付宝", AliPayStrategy.class),
    WECHAT_PAY("微信", WeChatPayStrategy.class),
    UNION_PAY("银联", UnionPayStrategy.class),
    NULL_PAY("不支付", NullPay.class),;

    private String name;
    private Class<? extends PayStrategy> strategyClazz;

    PayType(String name, Class<? extends PayStrategy> strategyClazz) {
        this.name = name;
        this.strategyClazz = strategyClazz;
    }

    public String getName() {
        return name;
    }

    public Class<? extends PayStrategy> getStrategyClazz() {
        return strategyClazz;
    }
}
