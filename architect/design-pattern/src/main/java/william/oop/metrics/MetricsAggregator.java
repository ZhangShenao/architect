package william.oop.metrics;

import java.util.List;

/**
 * @Author zhangshenao
 * @Date 2021-01-18
 * @Description 指标聚合器
 */
public interface MetricsAggregator {
    /**
     * 聚合原始指标，生成统计信息
     */
    RequestStats aggregate(List<RequestInfo> infos);
}
