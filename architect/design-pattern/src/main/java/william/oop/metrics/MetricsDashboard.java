package william.oop.metrics;

/**
 * @Author zhangshenao
 * @Date 2021-01-18
 * @Description 指标展示看板
 */
public interface MetricsDashboard {
    /**
     * 展示统计信息
     */
    void showStats(String apiName, long startTime, long endTime);
}
