package william.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhangshenao
 * @Date 2020-02-10
 * @Description
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @GetMapping("/queryName")
    public String queryName() {
        return "兰蔻小黑瓶";
    }
}
