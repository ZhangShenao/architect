package william.designpattern.strategy.promotion;

/**
 * @Author zhangshenao
 * @Date 2020-03-16
 * @Description 拼团促销
 */
public class GroupBuyPromotion implements PromotionStrategy {
    @Override
    public void promotion() {
        System.err.println("拼团促销");
    }
}
