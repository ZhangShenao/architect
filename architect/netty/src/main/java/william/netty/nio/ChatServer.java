package william.netty.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/23 15:46
 * @Description:聊天程序服务端
 */
public class ChatServer {
    private static Map<String, SocketChannel> clients = new ConcurrentHashMap<>();
    private static Selector selector;

    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8080));
        System.err.println("Server 绑定端口 : 8080");
        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            int select = selector.select();
            if (select <= 0) {
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();

                if (selectionKey.isAcceptable()) {
                    acceptClient((ServerSocketChannel) selectionKey.channel());
                } else if (selectionKey.isReadable()) {
                    broadcastMessage((SocketChannel) selectionKey.channel());
                }
            }
        }
    }

    private static void acceptClient(ServerSocketChannel serverSocketChannel) throws Exception {
        SocketChannel client = serverSocketChannel.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
        clients.putIfAbsent(UUID.randomUUID().toString(), client);
        System.err.println("客户端连接: " + client);
    }

    private static void broadcastMessage(SocketChannel channel) throws Exception {
        ByteBuffer readBuf = ByteBuffer.allocate(1024);
        int read = channel.read(readBuf);
        if (read <= 0) {
            return;
        }
        readBuf.flip();
        Charset charset = Charset.forName("UTF-8");
        String content = String.valueOf(charset.decode(readBuf).array());
        System.err.println("Client: " + channel + ", content: " + content);
        for (Map.Entry<String, SocketChannel> entry : clients.entrySet()) {
            String key = entry.getKey();
            SocketChannel client = entry.getValue();
            String msg;
            if (client == channel) {
                msg = "[self]: " + content;
            } else {
                msg = "[" + key + "]" + content;
            }
            ByteBuffer writeBuf = ByteBuffer.wrap(msg.getBytes(charset));
//            writeBuf.flip();      使用wrap()方法创建的Buffer，不需要调用flip
            client.write(writeBuf);
        }
    }
}
