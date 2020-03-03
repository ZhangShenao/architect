package william.designprinciple.composite;

/**
 * @Author zhangshenao
 * @Date 2020-03-03
 * @Description
 */
public class ProductDao {
    private DBConnection connection;

    public ProductDao(DBConnection connection) {
        this.connection = connection;
    }

    public void updateProduct() {
        connection.execute("update product");
    }
}
