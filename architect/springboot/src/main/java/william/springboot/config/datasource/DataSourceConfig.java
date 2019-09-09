package william.springboot.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/10 17:00
 * @Description:数据源配置
 */
@Configuration
@EnableTransactionManagement    //开启Spring事务管理
public class DataSourceConfig {
    private static Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    //配置Druid监控的Servlet
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        registrationBean.setServlet(new StatViewServlet());
//        registrationBean.setAsyncSupported(true);
        registrationBean.addUrlMappings("/druid/*");
        registrationBean.addInitParameter("allow", "127.0.0.1");
//        registrationBean.addInitParameter("deny","/deny");
//        registrationBean.addInitParameter("loginUsername", "admin");
//        registrationBean.addInitParameter("loginPassword", "admin");
        logger.info("Druid StatViewServlet Registered : {} ", registrationBean);
        return registrationBean;
    }

    //配置Druid监控的Filter
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new WebStatFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico, /druid/*");
        logger.info("Druid WebStatFilter Registered : {} ", registrationBean);
        return registrationBean;
    }

    //配置Druid数据源
    @Bean
    public DataSource dataSource(DruidProperties props) throws SQLException {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(props.getDriverClassName());
        ds.setUrl(props.getUrl());
        ds.setUsername(props.getUsername());
        ds.setPassword(props.getPassword());
        ds.setInitialSize(props.getInitialSize());
        ds.setMinIdle(props.getMinIdle());
        ds.setMaxActive(props.getMaxActive());
        ds.setTimeBetweenEvictionRunsMillis(props.getTimeBetweenEvictionRunsMillis());
        ds.setMinEvictableIdleTimeMillis(props.getMinEvictableIdleTimeMillis());
        ds.setValidationQuery(props.getValidationQuery());
        ds.setTestWhileIdle(props.isTestWhileIdle());
        ds.setTestOnBorrow(props.isTestOnBorrow());
        ds.setTestOnReturn(props.isTestOnReturn());
        ds.setPoolPreparedStatements(props.isPoolPreparedStatements());
        ds.setMaxPoolPreparedStatementPerConnectionSize(props.getMaxPoolPreparedStatementPerConnectionSize());
        ds.setFilters(props.getFilters());
        ds.setConnectionProperties(props.getConnectionProperties());
        logger.info("Druid Datasource Configured : {} ", ds);
        return ds;
    }

    //配置事务管理器
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager txManager = new DataSourceTransactionManager();
        txManager.setDataSource(dataSource);
        return txManager;
    }
}
