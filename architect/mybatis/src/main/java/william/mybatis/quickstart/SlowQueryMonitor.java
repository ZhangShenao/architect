package william.mybatis.quickstart;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.jdbc.PreparedStatementLogger;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/4/7 09:49
 * @Description:慢查询监控器 基于MyBatis的插件机制, 自定义慢查询插件, 监控慢查询
 */

//标记拦截的方法签名
@Intercepts({@Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class})})
public class SlowQueryMonitor implements Interceptor {
    private long threshold;

    @Override
    public Object intercept(Invocation invocation) throws Exception {
        long start = System.currentTimeMillis();
        Object retVal = invocation.proceed();
        long cost = System.currentTimeMillis() - start;
        if (cost > threshold) {
            Statement statement = (Statement) invocation.getArgs()[0];
            MetaObject metaObject = SystemMetaObject.forObject(statement);
            //获取动态代理的PreparedStatementLogger对象
            PreparedStatementLogger logger = (PreparedStatementLogger) metaObject.getValue("h");
            PreparedStatement preparedStatement = logger.getPreparedStatement();
            System.err.println(String.format("慢查询! [时长: %s, sql: %s]", cost, preparedStatement));
        }
        return retVal;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        threshold = Long.parseLong(properties.getProperty("threshold"));
    }
}
