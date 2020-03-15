package william.designpattern.template;

import java.util.Optional;

import javax.sql.DataSource;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description 模板方法模式:父类定义一个算法的骨架,并允许子类为一个或者多个步骤提供具体实现。
 * 模板方法使得子类可以在不改变算法结构的情况下,重新定义算法的某些步骤。
 * 模板方法属于行为性设计模式。
 */
public class Client {
    public static void main(String[] args) {
        DataSource dataSource = null;
        Optional.ofNullable(dataSource).ifPresent(d -> {
            JdbcExecutor executor = new JdbcQueryExecutor(dataSource);
            try {
                executor.execute("select * from user");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
