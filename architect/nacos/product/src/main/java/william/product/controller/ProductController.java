package william.product.controller;

import org.springframework.beans.factory.annotation.Value;
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
    @Value("${server.port}")
    private int port;

    @GetMapping("/queryName")
    public String queryName() {
        System.err.println("product-service被调用,port: " + port);
        return "兰蔻小黑瓶";
    }
}
