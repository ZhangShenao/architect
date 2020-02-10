package william.order.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author zhangshenao
 * @Date 2020-02-10
 * @Description
 */
@Getter
@Setter
public class OrderVO {
    private String orderId;
    private String productName;

    public static OrderVO valueOf(String orderId, String productName) {
        OrderVO vo = new OrderVO();
        vo.orderId = orderId;
        vo.productName = productName;
        return vo;
    }

    public static OrderVO empty() {
        return new OrderVO();
    }
}
