package william.mybatis.quickstart.mapper;

import william.mybatis.quickstart.entity.UserEntity;

import java.sql.*;

/**
 * @Author: ZhangShenao
 * @Date: 2019/5/23 13:10
 * @Description:
 */
public class JDBCMain {
    public static void main(String[] args) {
        try {
            //1.注册数据库驱动
            Class.forName("com.mysql.jdbc.Driver");

            //2.创建数据库连接
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/user", "root", "123");

            //3.创建Statement
            String sql = "select * from `user` where `id` = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //4.设置SQL参数
            preparedStatement.setLong(0, 1L);

            //5.执行SQL语句,获取结果集
            ResultSet resultSet = preparedStatement.executeQuery();

            //6.将结果集封装成entity
            UserEntity entity = new UserEntity();
            entity.setId(resultSet.getLong(0));
            entity.setUserName(resultSet.getString(1));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //7.手动释放资源,省略

        }
    }
}
