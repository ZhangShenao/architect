package william.netty.practise;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author zhangshenao
 * @Date 2021-07-25
 * @Description HttpServer第二版, 采用 Thread Per Connection 模式
 * wrk -c50 -d60s -t4  http://localhost:8002
 * 吞吐量   27.49
 * <p>
 * 存在的问题：1.频繁创建和销毁线程,资源开销大 2.并发量上来了之后,所需的线程数量太多,系统负载过大
 */
public class HttpServer2 {
    public static void main(String[] args) throws Exception {
        new HttpServer2().start();
    }

    public void start() throws Exception {
        //创建 ServerSocket,并绑定端口
        ServerSocket serverSocket = new ServerSocket(8002);
        System.err.println("HttpServer2 Start on Port: 8002");
        while (true) {
            //接收客户端连接
            Socket socket = serverSocket.accept();

            //创建一个新线程与客户端通信,处理业务逻辑
            new Thread(() -> {
                try {
                    service(socket);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private void service(Socket socket) throws Exception {
        //模拟业务处理耗时
        Thread.sleep(20L);

        //Http Response
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        printWriter.println("HTTP/1.1 200 OK");
        printWriter.println("Content-Type:text/html;charset=utf-8");
        String body = "Hello,NIO~";
        printWriter.println("Content-Length:" + body.getBytes().length);
        printWriter.println();
        printWriter.write(body);
        printWriter.close();
        socket.close();
        System.err.println("Handle Http Request");
    }
}
