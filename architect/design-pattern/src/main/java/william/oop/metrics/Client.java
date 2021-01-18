package william.oop.metrics;

/**
 * @Author zhangshenao
 * @Date 2021-01-18
 * @Description
 */
public class Client {
    public static void main(String[] args) {
        //指标采集
        MetricsStorage storage = new MemoryMetricsStorage();
        MetricsCollector collector = new SimpleMetricsCollector(storage);
        collector.collect(RequestInfo.ofNow("login", 201D));
        collector.collect(RequestInfo.ofNow("login", 210D));
        collector.collect(RequestInfo.ofNow("login", 189D));
        collector.collect(RequestInfo.ofNow("register", 312D));
        collector.collect(RequestInfo.ofNow("register", 295D));

        //指标统计展示
        MetricsAggregator aggregator = new SimpleMetricsAggregator();
        MetricsDashboard dashboard = new EmailMetricsDashboard(storage, aggregator);
        dashboard.showStats("login", 0L, System.currentTimeMillis());
    }
}
