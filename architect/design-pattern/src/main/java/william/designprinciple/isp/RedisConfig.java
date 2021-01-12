package william.designprinciple.isp;

import lombok.Getter;

/**
 * @Author zhangshenao
 * @Date 2021-01-12
 * @Description Redis 配置
 */
@Getter
public class RedisConfig implements ReloadableResource, MonitorableResource {
    private ConfigSource configSource;

    public RedisConfig(ConfigSource configSource) {
        this.configSource = configSource;
    }

    private String url;

    private String userName;

    private String password;

    private long connectionTimeout;

    @Override
    public void reload() {
        System.err.println("Reload Redis Config...");
    }

    @Override
    public void monitor() {
        System.err.println("Monitor Redis Metrics...");
    }
}
