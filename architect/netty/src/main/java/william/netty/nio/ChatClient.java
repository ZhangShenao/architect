package william.netty.nio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/23 16:53
 * @Description:聊天程序客户端
 */
public class ChatClient {
    private static Selector selector;

    private static ExecutorService executor = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws Exception {
        selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));

        while (true) {
            int select = selector.select();
            if (select <= 0) {
                continue;
            }
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isConnectable()) {
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    if (channel.isConnectionPending()) {
                        channel.finishConnect();
                        /*ByteBuffer writeBuf = ByteBuffer.allocate(1024);
                        writeBuf.put((LocalDateTime.now() + "连接成功").getBytes());
                        writeBuf.flip();
                        channel.write(writeBuf);*/
                        send2Server(channel);
                    }
                    channel.register(selector,SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    showMessage((SocketChannel) selectionKey.channel());
                }
            }
            iterator.remove();
        }
    }

    private static void send2Server(SocketChannel channel) {
        executor.submit(() -> {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                buffer.clear();
                String msg = reader.readLine();
                buffer.put(msg.getBytes());
                buffer.flip();
                channel.write(buffer);
            }
        });
    }

    private static void showMessage(SocketChannel channel) throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        buffer.flip();
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);
        System.err.println(new String(bytes));
    }
}
