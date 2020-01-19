package william.concurrent.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author zhangshenao
 * @Date 2020-01-19
 * @Description BIO通信服务端
 */
public class Server {
    public static void main(String[] args) throws Exception {
        //创建ServerSocket,并绑定指定端口
        ServerSocket serverSocket = new ServerSocket(9999);

        System.err.println("Server Started...");

        //监听客户端连接
        Socket socket = serverSocket.accept();

        //获取输入流,接收客户端信息
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        //打印客户端消息
        String msg;

        while (!("bye".equalsIgnoreCase(msg = reader.readLine()))) {
            System.err.println("From Client: " + msg);
        }

        //关闭连接,释放资源
        reader.close();
        socket.close();
        serverSocket.close();
    }
}
