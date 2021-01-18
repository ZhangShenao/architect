package william.oop.metrics;

import java.util.List;

import org.springframework.util.CollectionUtils;

/**
 * @Author zhangshenao
 * @Date 2021-01-18
 * @Description 邮件指标看板
 */
public class EmailMetricsDashboard implements MetricsDashboard {
    private MetricsStorage storage;

    private MetricsAggregator aggregator;

    //依赖注入
    public EmailMetricsDashboard(MetricsStorage storage, MetricsAggregator aggregator) {
        this.storage = storage;
        this.aggregator = aggregator;
    }

    @Override
    public void showStats(String apiName, long startTime, long endTime) {
        List<RequestInfo> infos = storage.listByTimeRange(apiName, startTime, endTime);
        if (CollectionUtils.isEmpty(infos)) {
            return;
        }
        RequestStats stats = aggregator.aggregate(infos);
        System.err.println("邮件发送指标统计信息");
    }
}
