package william.designpattern.template;

import java.sql.Statement;

import javax.sql.DataSource;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description
 */
public class JdbcQueryExecutor extends JdbcExecutor {
    public JdbcQueryExecutor(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void doExecute(Statement statement, String sql) throws Exception {
        statement.executeQuery(sql);
        System.err.println("执行查询语句");
    }
}
