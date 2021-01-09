package william.oop.auth;

/**
 * @Author zhangshenao
 * @Date 2021-01-09
 * @Description 对请求的抽象
 */
public interface Request {
    String appId();

    String secret();

    long timestamp();

    RequestAddr requestAddr();
}
