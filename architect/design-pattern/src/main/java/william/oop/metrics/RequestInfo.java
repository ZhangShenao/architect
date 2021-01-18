package william.oop.metrics;

import org.springframework.util.StringUtils;

import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2021-01-18
 * @Description 封装请求信息
 */
@Data
public class RequestInfo {
    private String apiName; //API 名称
    private double costTime;    //处理耗时
    private long timestamp; //时间戳

    public boolean isValid() {
        return (StringUtils.hasText(apiName) && timestamp > 0L);
    }

    public static RequestInfo ofNow(String apiName, double costTime) {
        RequestInfo info = new RequestInfo();
        info.apiName = apiName;
        info.costTime = costTime;
        info.timestamp = System.currentTimeMillis();
        return info;
    }
}
