package william.spring.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/12/5 16:57
 * @Description:
 */
@Configuration
@ComponentScan
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class AopConfig {
}
