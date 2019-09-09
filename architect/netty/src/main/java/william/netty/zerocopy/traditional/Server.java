package william.netty.zerocopy.traditional;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/26 15:20
 * @Description:传统Socket模式的服务端
 */
public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.err.println("Server Start");
        while (true) {
            //以流的方式读取客户端传递过来的数据
            Socket socket = serverSocket.accept();
            DataInputStream in = new DataInputStream(socket.getInputStream());

            byte[] bytes = new byte[4096];
            while (true) {
                int read = in.read(bytes, 0, bytes.length);
                if (read <= 0) {
                    break;
                }
            }
        }

    }
}
