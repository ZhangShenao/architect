package william.oop.metrics;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Author zhangshenao
 * @Date 2021-01-18
 * @Description 将指标保存在内存中
 */
public class MemoryMetricsStorage implements MetricsStorage {
    private Map<String, List<RequestInfo>> infos = new HashMap<>();

    @Override
    public void save(RequestInfo info) {
        String apiName = info.getApiName();
        infos.putIfAbsent(apiName, new ArrayList<>());
        infos.get(apiName).add(info);
    }

    @Override
    public List<RequestInfo> listByTimeRange(String apiName, long startTime, long endTime) {
        return Optional.ofNullable(infos.get(apiName))
                .orElse(Collections.emptyList())
                .stream()
                .filter(x -> x.getTimestamp() > startTime && x.getTimestamp() < endTime)
                .collect(toList());
    }
}
