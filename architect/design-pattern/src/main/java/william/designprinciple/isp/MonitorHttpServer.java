package william.designprinciple.isp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Author zhangshenao
 * @Date 2021-01-12
 * @Description 监控 HTTP 服务
 */
public class MonitorHttpServer {
    private String host;
    private int port;
    private Map<String, List<MonitorableResource>> monitors;

    public MonitorHttpServer(String host, int port) {
        this.host = host;
        this.port = port;
        this.monitors = new HashMap<>();
    }

    public void addMonitorableResource(String key, MonitorableResource resource) {
        monitors.putIfAbsent(key, new LinkedList<>());
        monitors.get(key).add(resource);
    }

    public void monitor(String key) {
        Optional.ofNullable(monitors.get(key)).ifPresent(x -> x.forEach(MonitorableResource::monitor));
    }
}
