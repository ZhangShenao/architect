package william.netty.nio;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/23 11:18
 * @Description:
 */
public class TestFileLock {
    @Test
    public void testFileLock() throws Exception {
        RandomAccessFile file = new RandomAccessFile("out.txt", "rw");
        FileChannel channel = file.getChannel();

        //获取文件锁
        FileLock fileLock = channel.lock(0,Integer.MAX_VALUE,false);
        System.err.println("valid: " + fileLock.isValid());
        System.err.println("shared: " + fileLock.isShared());

        //释放锁
        fileLock.release();

        file.close();

    }
}
