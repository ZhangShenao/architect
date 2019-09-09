package william.netty.nio;

import org.junit.Test;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/23 19:51
 * @Description:
 */
public class CharsetDemo {
    @Test
    public void testCharset() throws Exception {
        RandomAccessFile in = new RandomAccessFile("in.txt", "r");
        RandomAccessFile out = new RandomAccessFile("out.txt", "rw");
        FileChannel inChannel = in.getChannel();
        FileChannel outChannel = out.getChannel();
        long length = new File("in.txt").length();
        MappedByteBuffer inBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, length);
        Charset charset = Charset.forName("iso-8859-1");
        CharsetDecoder decoder = charset.newDecoder();
        CharsetEncoder encoder = charset.newEncoder();

        //解码
        CharBuffer charBuffer = decoder.decode(inBuffer);

        ByteBuffer outBuffer = encoder.encode(charBuffer);

        outChannel.write(outBuffer);

        //获取当前系统支持的所有Charset
//        Charset.availableCharsets().forEach((s, charset1) -> System.err.println(s));
        out.close();
        in.close();
    }
}
