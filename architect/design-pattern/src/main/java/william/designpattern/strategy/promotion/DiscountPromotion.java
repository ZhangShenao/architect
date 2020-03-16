package william.designpattern.strategy.promotion;

/**
 * @Author zhangshenao
 * @Date 2020-03-16
 * @Description
 */
public class DiscountPromotion implements PromotionStrategy {
    @Override
    public void promotion() {
        System.err.println("折扣促销");
    }
}
