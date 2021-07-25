package william.netty.practise;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @Author zhangshenao
 * @Date 2021-07-25
 * @Description HttpServer第三版, 采用线程池模式
 * wrk -c50 -d60s -t4  http://localhost:8003
 * 吞吐量： 25.40
 */
public class HttpServer3 {
    //因为系统的IO密集型的,线程等待IO操作的时间较长,因此可以适当增大线程池的大小,提升CPU利用率
    private static final Executor executor =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 4);

    public static void main(String[] args) throws Exception {
        new HttpServer3().start();
    }

    public void start() throws Exception {
        //创建 ServerSocket,并绑定端口
        ServerSocket serverSocket = new ServerSocket(8003);
        System.err.println("HttpServer3 Start on Port: 8003");
        while (true) {
            //接收客户端连接
            Socket socket = serverSocket.accept();

            //创建一个新线程与客户端通信,处理业务逻辑
            executor.execute(() -> {
                try {
                    service(socket);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
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
