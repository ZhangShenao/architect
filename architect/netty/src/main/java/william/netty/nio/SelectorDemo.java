package william.netty.nio;

import org.junit.Test;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/23 12:41
 * @Description:
 */
public class SelectorDemo {
    @Test
    public void test() throws Exception {
        int[] ports = new int[5];
        for (int i = 0; i < 5; i++) {
            ports[i] = 8080 + i;
        }

        //开启Selector
        Selector selector = Selector.open();
        for (int i = 0; i < 5; i++) {
            //创建ServerSocketChannel
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            //设置为非阻塞
            serverSocketChannel.configureBlocking(false);
            //获取ServerSocket
            ServerSocket socket = serverSocketChannel.socket();
            //绑定端口
            InetSocketAddress address = new InetSocketAddress(ports[i]);
            socket.bind(address);

            //向Selector注册ServerSocketChannel，注册感兴趣的事件，并返回一个SelectionKey
            SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.err.println("服务端监听端口: " + ports[i]);
        }

        while (true) {
            int num = selector.select();
            System.err.println("num: " + num);

            //获取所有的SelectionKey
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.err.println("Selected Keys Num: " + selectionKeys.size());
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                //删除处理完毕的SelectionKey
                iterator.remove();

                //处理监听就绪事件,获取与客户端的连接,并注册读事件
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                    System.err.println("获取客户端连接: " + socketChannel);
                }
                //处理读就绪事件,从Channel中读取数据
                else if (selectionKey.isReadable()){
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(512);
                    int readBytes = 0;
                    while (true){
                        buffer.clear();
                        int read = channel.read(buffer);
                        if (read <= 0){
                            break;
                        }
                        readBytes += read;
                        buffer.flip();
                        //将读取到的数据写回
                        channel.write(buffer);
                    }
                    System.err.println("Read Bytes: " + readBytes);
                }

            }

        }
    }
}
