package william.designprinciple.isp;

import lombok.Getter;

/**
 * @Author zhangshenao
 * @Date 2021-01-12
 * @Description MySQL 配置
 */
@Getter
public class MySQLConfig implements MonitorableResource {
    private ConfigSource configSource;

    public MySQLConfig(ConfigSource configSource) {
        this.configSource = configSource;
    }

    private String url;

    private String userName;

    private String password;

    private long connectionTimeout;

    @Override
    public void monitor() {
        System.err.println("Monitor MySQL Metrics...");
    }
}
