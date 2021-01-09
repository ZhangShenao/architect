package william.oop.auth;

/**
 * @Author zhangshenao
 * @Date 2021-01-09
 * @Description MySQL 秘钥存储器
 */
public class MySQLCredentialStorage implements CredentialStorage {

    @Override
    public void save(String appId, String password) {
        System.err.println("Save Credential");
    }

    @Override
    public String find(String appId) {
        System.err.println("Find Credential");
        return "TEST";
    }
}
