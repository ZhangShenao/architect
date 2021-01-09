package william.oop.auth;

/**
 * @Author zhangshenao
 * @Date 2021-01-09
 * @Description 请求 URL, 针对 API 服务
 */
public class RequestUrl implements RequestAddr {
    private String url;

    public RequestUrl(String url) {
        this.url = url;
    }

    @Override
    public String addr() {
        return url;
    }
}
