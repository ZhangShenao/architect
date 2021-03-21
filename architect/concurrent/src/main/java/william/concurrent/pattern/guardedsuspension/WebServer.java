package william.concurrent.pattern.guardedsuspension;

import java.util.Objects;

/**
 * @Author zhangshenao
 * @Date 2021-03-21
 * @Description Web服务器，通过Guarded-Suspension模式，将异步Message转换成同步请求
 */
public class WebServer {
    //处理同步请求
    public Response handleSyncRequest(String key, String payload) {
        //创建异步消息
        Message m = Message.valueOf(key, payload);

        //创建GuardedObject对象
        GuardedObject<Message> guardedObject = GuardedObject.create(key, 2L);

        //异步发送消息
        sendMessageAsync(m);

        //等待消息结果
        Message r = guardedObject.get(Objects::nonNull);

        //返回同步结果
        return Response.valueOf(r.getPayload());
    }


    //异步发送消息
    private void sendMessageAsync(Message m) {
        System.err.println("发送异步消息：" + m.getPayload());
    }

    //消费消息
    public void onMessage(Message m) {
        //唤醒等待的线程
        GuardedObject.fireEvent(m.getId(), m);
    }

}
