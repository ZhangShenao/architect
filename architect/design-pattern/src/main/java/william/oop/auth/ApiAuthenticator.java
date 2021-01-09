package william.oop.auth;

/**
 * @Author zhangshenao
 * @Date 2021-01-09
 * @Description API 接口鉴权器
 */
public class ApiAuthenticator implements Authenticator<ApiRequest> {
    @Override
    public void auth(ApiRequest request) {
        String appId = request.appId();
        String secret = request.secret();

        AuthTokenCodec codec = new Base64AuthTokenCodec();
        AuthToken token = codec.encode(request);
        if (token.isExpired()) {
            throw new RuntimeException("Token Expired!!");
        }

        CredentialStorage storage = new MySQLCredentialStorage();
        String password = storage.find(appId);
        if (token.isMatch(password)) {
            throw new RuntimeException("Token Not Match!!");
        }
    }
}
