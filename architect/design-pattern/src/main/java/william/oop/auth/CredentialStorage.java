package william.oop.auth;

/**
 * @Author zhangshenao
 * @Date 2021-01-09
 * @Description 秘钥存储器
 */
public interface CredentialStorage {
    void save(String appId, String password);

    String find(String appId);
}
