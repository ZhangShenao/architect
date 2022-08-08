package william.redis.config;

import org.redisson.Redisson;
import org.redisson.client.codec.ScanCodec;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zhangshenao
 * @Date 2020-02-03
 * @Description Redisson配置
 */
@Configuration
public class RedissonConfig {
    @Bean
    public Redisson redisson() {
        //配置主从架构的客户端
        Config config = new Config();
        config.useMasterSlaveServers()
                .setMasterAddress("redis://127.0.0.1:6379");
//                .addSlaveAddress("redis://127.0.0.1:6380", "redis://127.0.0.1:6381");
        config.setCodec(new StringCodec());   //设置编解码方式——String
        return (Redisson) Redisson.create(config);
    }
}
