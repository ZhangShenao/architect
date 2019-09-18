package william.designpattern.template;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description 抽象父类, 定了了算法的整体流程, 并留出hooks方法交给子类实现
 */
public abstract class JdbcExecutor {
    private DataSource dataSource;

    public JdbcExecutor(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public void execute(String sql) throws Exception {
        //1. 创建数据库连接
        Connection connection = createConnection(dataSource);

        //2. 创建Statement
        Statement statement = createStatement(connection, sql);

        //3. 执行具体的语句
        doExecute(statement, sql);

        //4. 关闭资源
        release(statement, connection);
    }

    private Connection createConnection(DataSource dataSource) throws SQLException {
        return dataSource.getConnection();
    }

    private Statement createStatement(Connection conn, String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    protected abstract void doExecute(Statement statement, String sql) throws Exception;

    private void release(AutoCloseable... resources) throws Exception {
        for (AutoCloseable x : resources) {
            x.close();
        }
    }
}
