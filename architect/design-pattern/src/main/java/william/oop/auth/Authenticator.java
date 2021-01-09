package william.oop.auth;

/**
 * @Author zhangshenao
 * @Date 2021-01-09
 * @Description 鉴权器接口
 */
public interface Authenticator<T extends Request> {
    /**
     * 对请求进行鉴权
     */
    void auth(T request);
}
