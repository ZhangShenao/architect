package william.mybatis.quickstart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Author zhangshenao
 * @Date 2020-12-11
 * @Description 原生JDBC编程示例
 */
public class RawJdbcDemo {
    public static void main(String[] args) throws Exception {
        //1. 加载数据库驱动
        Class.forName("com.mysql.jdbc.Driver");

        //2. 创建数据库连接
        String url = "jdbc:mysql://localhost:3306/test";
        String userName = "root";
        String password = "123456";
        Connection connection = DriverManager.getConnection(url, userName, password);

        //3. 创建Statement
        String sql = "select * from `test`";
        PreparedStatement statement = connection.prepareStatement(sql);

        //4. 执行查询操作,获取ResultSet结果集
        ResultSet resultSet = statement.executeQuery();

        //5. 对结果集进行解析
    }
}
