package william.designpattern.strategy.promotion;

/**
 * @Author zhangshenao
 * @Date 2020-03-16
 * @Description
 */
public class CashBackPromotion implements PromotionStrategy {
    @Override
    public void promotion() {
        System.err.println("返现促销");
    }
}
