package william.oop.metrics;

import java.util.List;

/**
 * @Author zhangshenao
 * @Date 2021-01-18
 * @Description 简单指标聚合器
 */
public class SimpleMetricsAggregator implements MetricsAggregator {
    @Override
    public RequestStats aggregate(List<RequestInfo> infos) {
        System.err.println("Do Metrics Aggregation...");
        return new RequestStats();
    }
}
