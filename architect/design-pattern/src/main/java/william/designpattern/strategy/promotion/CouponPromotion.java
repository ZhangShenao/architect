package william.designpattern.strategy.promotion;

/**
 * @Author zhangshenao
 * @Date 2020-03-16
 * @Description
 */
public class CouponPromotion implements PromotionStrategy {
    @Override
    public void promotion() {
        System.err.println("优惠券促销");
    }
}
