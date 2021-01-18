package william.oop.metrics;

import java.util.List;

/**
 * @Author zhangshenao
 * @Date 2021-01-18
 * @Description 指标存储器
 */
public interface MetricsStorage {
    /**
     * 保存指标
     */
    void save(RequestInfo info);

    /**
     * 查询指定时间范围内的指标数据
     */
    List<RequestInfo> listByTimeRange(String apiName, long startTime, long endTime);
}
