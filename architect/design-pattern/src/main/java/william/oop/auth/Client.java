package william.oop.auth;

/**
 * @Author zhangshenao
 * @Date 2021-01-09
 * @Description
 */
public class Client {
    public static void main(String[] args) {
        RequestUrl url = new RequestUrl("www.baidu.com");
        Request request = new ApiRequest("appId", "secret", url);
        Authenticator authenticator = new ApiAuthenticator();
        authenticator.auth(request);
    }
}
