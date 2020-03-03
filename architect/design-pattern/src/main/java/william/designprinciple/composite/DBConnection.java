package william.designprinciple.composite;

/**
 * @Author zhangshenao
 * @Date 2020-03-03
 * @Description 数据库连接
 */
public interface DBConnection {
    void execute(String sql);
}
