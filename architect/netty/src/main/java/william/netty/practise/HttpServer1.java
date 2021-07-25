package william.netty.practise;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author zhangshenao
 * @Date 2021-07-25
 * @Description 基于最简单的Socket实现Http服务器, Server端串行执行每个客户端的请求
 * wrk -c50 -d60s -t4  http://localhost:8001
 * 吞吐量： 5.67
 * 存在的问题：Server端对每个请求串行执行，性能根本上不去
 */
public class HttpServer1 {
    public static void main(String[] args) throws Exception {
        HttpServer1 server = new HttpServer1();
        server.start();
    }

    public void start() throws Exception {
        //创建 ServerSocket,并绑定端口
        ServerSocket serverSocket = new ServerSocket(8001);
        System.err.println("HttpServer1 Start on Port: 8001");
        while (true) {
            //接收客户端连接
            Socket socket = serverSocket.accept();

            //与客户端通信,处理业务逻辑
            service(socket);
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
