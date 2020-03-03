package william.designprinciple.composite;

/**
 * @Author zhangshenao
 * @Date 2020-03-03
 * @Description 合成复用原则:尽量多使用组合,少使用继承
 */
public class Client {
    public static void main(String[] args) {
        ProductDao productDao = new ProductDao(new MySQLConnection());
        productDao.updateProduct();
    }
}
