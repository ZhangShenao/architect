package william.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import william.redis.lua.PayService;

/**
 * @Auther: ZhangShenao
 * @Date 2022/8/11 12:00 PM
 * @Description: 支付API
 */
@RestController
public class PayController {
    @Autowired
    private PayService payService;

    @GetMapping("/pay")
    public String pay(@RequestParam("order_id") long orderId) {
        return payService.pay(orderId);
    }
}
