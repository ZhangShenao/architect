package william.designpattern.strategy.promotion;

/**
 * @Author zhangshenao
 * @Date 2020-03-16
 * @Description
 */
public class Client {
    public static void main(String[] args) {
        PromotionStrategy promotionStrategy = PromotionFactory.determinePromotionStrategy(PromotionType.COUPON);
        promotionStrategy.promotion();
    }
}
