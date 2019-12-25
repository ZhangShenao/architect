package william.spring.profile;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

/**
 * @Author zhangshenao
 * @Date 2019-12-25
 * @Description
 */
@Configuration
@PropertySource("classpath:datasource.properties")
public class MainConfig implements EmbeddedValueResolverAware {
    @Value("${url}")
    private String url;

    @Value("${driverClass}")
    private String driverClass;

    private String userName;

    private String password;

    //属性解析
    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.userName = resolver.resolveStringValue("${userName}");
        this.password = resolver.resolveStringValue("${password}");
    }

    //根据不同环境,注入不同的Bean
    @Profile("dev")
    @Bean
    public DataSource devDataSource() {
        return buildDataSource("dev");
    }

    @Profile("test")
    @Bean
    public DataSource testDataSource() {
        return buildDataSource("test");
    }

    @Profile("prod")
    @Bean
    public DataSource prodDataSource() {
        return buildDataSource("prod");
    }

    private DataSource buildDataSource(String stage) {
        DataSource ds = new DataSource();
        ds.setDriverClass(driverClass);
        ds.setUrl(url);
        ds.setUserName(userName);
        ds.setPassword(password);
        ds.setStage(stage);
        return ds;
    }
}
