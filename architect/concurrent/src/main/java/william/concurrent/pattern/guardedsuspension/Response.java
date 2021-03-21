package william.concurrent.pattern.guardedsuspension;

import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2021-03-21
 * @Description 同步处理响应结果
 */
@Data
public class Response {
    private String resp;

    public static Response valueOf(String resp) {
        Response r = new Response();
        r.resp = resp;
        return r;
    }
}
