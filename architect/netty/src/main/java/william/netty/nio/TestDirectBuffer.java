package william.netty.nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/22 15:40
 * @Description:
 */
public class TestDirectBuffer {
    @Test
    public void testReadWrite() throws Exception {
        FileInputStream in = new FileInputStream("in.txt");
        FileOutputStream out = new FileOutputStream("out.txt");

        //创建DirectByteBuffer
        ByteBuffer buffer = ByteBuffer.allocateDirect(512);

        FileChannel inChannel = in.getChannel();
        FileChannel outChannel = out.getChannel();

        while (true){
            //将Buffer置为初始状态
            buffer.clear();
            int read = inChannel.read(buffer);
            if (read <= 0){
                break;
            }
            buffer.flip();
            outChannel.write(buffer);
        }

        out.close();
        in.close();
    }
}
