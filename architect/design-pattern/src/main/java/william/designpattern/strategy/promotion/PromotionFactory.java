package william.designpattern.strategy.promotion;

import static william.designpattern.strategy.promotion.PromotionType.CASH_BACK;
import static william.designpattern.strategy.promotion.PromotionType.COUPON;
import static william.designpattern.strategy.promotion.PromotionType.DISCOUNT;
import static william.designpattern.strategy.promotion.PromotionType.GROUP_BUY;
import static william.designpattern.strategy.promotion.PromotionType.NULL;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Author zhangshenao
 * @Date 2020-03-16
 * @Description 促销策略工厂
 */
public class PromotionFactory {
    private static final Map<PromotionType, PromotionStrategy> promotions;

    //初始化各策略
    static {
        promotions = new HashMap<>();
        promotions.put(CASH_BACK, new CashBackPromotion());
        promotions.put(COUPON, new CouponPromotion());
        promotions.put(DISCOUNT, new DiscountPromotion());
        promotions.put(GROUP_BUY, new GroupBuyPromotion());
        promotions.put(NULL, new NullPromotion());

    }

    public static PromotionStrategy determinePromotionStrategy(PromotionType promotionType) {
        return Optional.ofNullable(promotions.get(promotionType)).orElse(new NullPromotion());
    }
}
