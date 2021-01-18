package william.oop.metrics;

/**
 * @Author zhangshenao
 * @Date 2021-01-18
 * @Description 指标采集器
 */
public interface MetricsCollector {
    /**
     * 采集指标
     */
    void collect(RequestInfo info);

}
