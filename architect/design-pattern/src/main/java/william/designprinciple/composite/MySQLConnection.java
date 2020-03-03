package william.designprinciple.composite;

/**
 * @Author zhangshenao
 * @Date 2020-03-03
 * @Description
 */
public class MySQLConnection implements DBConnection {
    @Override
    public void execute(String sql) {
        System.err.println("MySQL数据库,执行: " + sql);
    }
}
