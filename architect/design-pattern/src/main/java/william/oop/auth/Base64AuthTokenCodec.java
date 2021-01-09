package william.oop.auth;

import java.util.Base64;

/**
 * @Author zhangshenao
 * @Date 2021-01-09
 * @Description 基于 Base64 的请求 token 编解码器
 */
public class Base64AuthTokenCodec implements AuthTokenCodec {
    private static final String DELIMITER = "_";

    @Override
    public AuthToken encode(Request request) {
        //根据appId、secret生成token
        String appId = request.appId();
        String secret = request.secret();
        byte[] b = (appId + DELIMITER + secret).getBytes();
        String token = new String(Base64.getEncoder().encode(b));
        return new AuthToken(appId, token, request.timestamp());
    }
}
