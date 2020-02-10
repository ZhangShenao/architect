package william.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import william.order.vo.OrderVO;

/**
 * @Author zhangshenao
 * @Date 2020-02-10
 * @Description
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    private static final String PRODUCT_SERVICE_NAME = "product-service";

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{orderId}")
    public OrderVO queryById(@PathVariable String orderId) {
        //获取服务实例
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(PRODUCT_SERVICE_NAME);
        if (CollectionUtils.isEmpty(serviceInstances)) {
            return OrderVO.empty();
        }

        ServiceInstance instance = serviceInstances.get(0);
        String uri = instance.getUri().toString();

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri + "/product/queryName", String.class);
        String productName = responseEntity.getBody();
        if (StringUtils.isEmpty(productName)) {
            return OrderVO.empty();
        }

        return OrderVO.valueOf(orderId, productName);
    }
}
