package william.designpattern.strategy.promotion;

/**
 * @Author zhangshenao
 * @Date 2020-03-16
 * @Description
 */
public class NullPromotion implements PromotionStrategy {
    @Override
    public void promotion() {
        System.err.println("无促销策略");
    }
}
