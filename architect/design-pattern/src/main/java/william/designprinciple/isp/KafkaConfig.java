package william.designprinciple.isp;

import lombok.Getter;

/**
 * @Author zhangshenao
 * @Date 2021-01-12
 * @Description Kafka 配置
 */
@Getter
public class KafkaConfig implements ReloadableResource {
    private ConfigSource configSource;

    public KafkaConfig(ConfigSource configSource) {
        this.configSource = configSource;
    }

    private String url;

    private String userName;

    private String password;

    private long connectionTimeout;

    @Override
    public void reload() {
        System.err.println("Reload Kafka Config...");
    }
}
