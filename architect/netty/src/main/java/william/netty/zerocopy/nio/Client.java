package william.netty.zerocopy.nio;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/26 16:58
 * @Description:
 */
public class Client {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(8080));

        FileChannel inChannel = new FileInputStream("/Users/william/Desktop/test_video.mp4").getChannel();
        long startTime = System.currentTimeMillis();

        //调用FileChannel的transferTo()方法，直接将文件从当前FileChannel传递到指定的Channel上
        //该方法会调用操作系统底层的操作，将字节从文件系统缓存中直接传递到指定的Channel上，而不需要经过Copy——这实现了Zero Copy
        long transferCount = inChannel.transferTo(0, inChannel.size(), socketChannel);
        long endTime = System.currentTimeMillis();
        System.err.println("Write to Server Complete,total: " + transferCount + " ,耗时：" + (endTime - startTime));
        inChannel.close();
        socketChannel.close();
    }
}
