package william.concurrent.pattern.guardedsuspension;

import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2021-03-21
 * @Description 模拟异步消息
 */
@Data
public class Message {
    private String id;

    private String payload;

    public static Message valueOf(String id, String payload) {
        Message m = new Message();
        m.id = id;
        m.payload = payload;
        return m;
    }
}
