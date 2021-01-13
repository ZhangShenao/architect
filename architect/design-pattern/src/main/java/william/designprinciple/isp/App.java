package william.designprinciple.isp;

/**
 * @Author zhangshenao
 * @Date 2021-01-12
 * @Description
 */
public class App {
    public static void main(String[] args) {
        ConfigSource configSource = new ConfigSource();

        RedisConfig redisConfig = new RedisConfig(configSource);
        KafkaConfig kafkaConfig = new KafkaConfig(configSource);
        MySQLConfig mySQLConfig = new MySQLConfig(configSource);

        //资源热更新
        new ReloadConfigScheduler(0L, 5000L, redisConfig).run();
        new ReloadConfigScheduler(0L, 5000L, kafkaConfig).run();

        //资源监控
        MonitorHttpServer server = new MonitorHttpServer("localhost", 8080);
        server.addMonitorableResource("redis", redisConfig);
        server.addMonitorableResource("mysql", mySQLConfig);
        server.monitor("redis");
        server.monitor("mysql");
    }
}
