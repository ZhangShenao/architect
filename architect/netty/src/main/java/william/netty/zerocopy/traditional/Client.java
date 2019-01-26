package william.netty.zerocopy.traditional;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/26 15:31
 * @Description:传统Socket模式的客户端
 */
public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(8080));

        //读取指定文件，以流的方式写到服务器端
        FileInputStream in = new FileInputStream("/Users/william/Desktop/test_video.mp4");
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        byte[] bytes = new byte[4096];
        int readCount = -1;
        int totalCount = 0;
        long startTime = System.currentTimeMillis();
        while (true) {
            readCount = in.read(bytes, 0, bytes.length);
            if (readCount <= 0) {
                break;
            }
            totalCount += readCount;
            out.write(bytes, 0, readCount);
        }
        long endTime = System.currentTimeMillis();
        //2000ms
        System.err.println("Write to Server Complete,total: " + totalCount + " ,耗时：" + (endTime - startTime));
        out.close();
        in.close();
        socket.close();
    }
}
