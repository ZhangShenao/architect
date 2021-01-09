package william.oop.auth;

/**
 * @Author zhangshenao
 * @Date 2021-01-09
 * @Description 请求 token 编解码器
 */
public interface AuthTokenCodec {
    AuthToken encode(Request request);
}
