package william.netty.zerocopy.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/26 16:09
 * @Description:NIO 服务端
 */
public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.setReuseAddress(true);     //设置超时状态的地址可重用
        serverSocket.bind(new InetSocketAddress(8080));
        System.err.println("Server Start");
        ByteBuffer buffer = ByteBuffer.allocate(4096);
        while (true) {
            SocketChannel channel = serverSocketChannel.accept();
            while (true) {
                int readCount = channel.read(buffer);
                if (readCount <= 0) {
                    break;
                }
                buffer.rewind();
            }
        }
    }
}
