package william.oop.auth;

/**
 * @Author zhangshenao
 * @Date 2021-01-09
 * @Description API 请求
 */
public class ApiRequest implements Request {
    private String appId;
    private String secret;
    private RequestUrl url;
    private long timestamp;

    public ApiRequest(String appId, String secret, RequestUrl url, long timestamp) {
        this.appId = appId;
        this.secret = secret;
        this.url = url;
        this.timestamp = timestamp;
    }

    public ApiRequest(String appId, String secret, RequestUrl url) {
        this.appId = appId;
        this.secret = secret;
        this.url = url;
    }

    @Override
    public String appId() {
        return appId;
    }

    @Override
    public String secret() {
        return secret;
    }

    @Override
    public long timestamp() {
        return timestamp;
    }

    @Override
    public RequestAddr requestAddr() {
        return url;
    }
}
