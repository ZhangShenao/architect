package william.oop.metrics;

import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2021-01-18
 * @Description 请求统计信息
 */
@Data
public class RequestStats {
    private double maxResponseTime;
    private double minResponseTime;
    private double avgResponseTime;
    private double p999ResponseTime;
    private double p99ResponseTime;
    private long count;
    private long tps;
}
