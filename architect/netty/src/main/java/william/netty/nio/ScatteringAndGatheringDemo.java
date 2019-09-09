package william.netty.nio;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/23 11:23
 * @Description:
 */
public class ScatteringAndGatheringDemo {
    @Test
    public void demo() throws Exception {
        //创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //绑定端口号
        InetSocketAddress address = new InetSocketAddress(8080);
        serverSocketChannel.bind(address);

        //构造ByteBuffer数组
        int messageLength = 2 + 3 + 4;
        ByteBuffer[] messages = new ByteBuffer[3];
        messages[0] = ByteBuffer.allocate(2);
        messages[1] = ByteBuffer.allocate(3);
        messages[2] = ByteBuffer.allocate(4);

        //监听客户端连接
        SocketChannel socketChannel = serverSocketChannel.accept();

        while (true) {
            int readBytes = 0;

            //从SocketChannel中将数据读取到Buffer数组中
            while (readBytes < messageLength) {
                long r = socketChannel.read(messages);
                readBytes += r;
                System.err.println("Read Bytes: " + readBytes);

                //读取过程中，打印每个Buffer的状态
                Arrays.stream(messages)
                        .map(buffer -> "position: " + buffer.position() + " limit: " + buffer.limit() + " capacity: " + buffer.capacity())
                        .forEach(System.err::println);
            }

            //读取数据结束，翻转，将数据写回给客户端
            Arrays.stream(messages).forEach(ByteBuffer::flip);
            int writeBytes = 0;
            while (writeBytes < messageLength) {
                long l = socketChannel.write(messages);
                writeBytes += l;
            }
            Arrays.stream(messages).forEach(ByteBuffer::clear);

            //打印读取和写入的状态
            System.err.println("readBytes: " + readBytes + ",writeBytes: " + writeBytes);
        }
    }
}
