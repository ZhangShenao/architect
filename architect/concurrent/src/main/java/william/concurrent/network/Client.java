package william.concurrent.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @Author zhangshenao
 * @Date 2020-01-19
 * @Description BIO通信客户端
 */
public class Client {
    public static void main(String[] args) throws Exception {
        //创建Socket,向Server发起连接
        Socket socket = new Socket("127.0.0.1", 9999);

        //从控制台获取输入流
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        //向服务器发送消息
        String msg;
        while ((msg = console.readLine()) != null) {
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println(msg);
            writer.flush();

            if ("bye".equalsIgnoreCase(msg)) {
                writer.close();
                console.close();
                socket.close();
            }
        }

    }
}
