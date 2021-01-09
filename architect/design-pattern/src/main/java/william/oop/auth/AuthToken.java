package william.oop.auth;

/**
 * @Author zhangshenao
 * @Date 2021-01-09
 * @Description 对鉴权token的抽象
 */
public class AuthToken {
    private static final long TOKEN_TTL = 1000L * 5; //token 过期时间

    private String appId;

    private String token;

    private long timestamp;

    public AuthToken(String appId, String token, long timestamp) {
        this.appId = appId;
        this.token = token;
        this.timestamp = timestamp;
    }

    public String getAppId() {
        return appId;
    }

    public String getToken() {
        return token;
    }

    public boolean isExpired() {
        return (System.currentTimeMillis() - timestamp) > TOKEN_TTL;
    }

    public boolean isMatch(String password) {
        return token.equalsIgnoreCase(password);
    }
}
