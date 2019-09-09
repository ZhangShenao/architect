package william.netty.nio;

import org.junit.Test;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/23 11:12
 * @Description:
 */
public class TestMappedBuffer {
    @Test
    public void testMap() throws Exception {
        RandomAccessFile file = new RandomAccessFile("out.txt", "rw");
        FileChannel channel = file.getChannel();

        //映射,获取MappedByteBuffer
        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        //通过MappedByteBuffer直接修改文件
        buffer.put(0,(byte)'a');
        buffer.put(2,(byte)'b');
        buffer.put(3,(byte)'c');

        file.close();
    }
}
