package william.oop.metrics;

/**
 * @Author zhangshenao
 * @Date 2021-01-18
 * @Description 简单的指标采集器
 */
public class SimpleMetricsCollector implements MetricsCollector {
    private MetricsStorage storage; //面向接口而非实现编程

    //依赖注入
    public SimpleMetricsCollector(MetricsStorage storage) {
        this.storage = storage;
    }

    @Override
    public void collect(RequestInfo info) {
        //存储采集到的指标
        if (info.isValid()) {
            storage.save(info);
        }
    }
}
