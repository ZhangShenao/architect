package william.interview.buffer;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * 处理中文乱码问题
 * UTF-8编码对中文为变长编码,一个中文字符可能占1~3个字节
 */
public class ChineseCodec {
    public static void main(String[] args) {
        //原始中文字符串
        String raw = "天生我材必有用，千金散去还复来";

        //采用UTF-8编码,将字符串编码成字节数组
        Charset charset = StandardCharsets.UTF_8;
        byte[] rawBytes = charset.encode(raw).array();

        //读取部分字符
        int partSize = 11;
        byte[] bytes = Arrays.copyOfRange(rawBytes, 0, partSize);


        ByteBuffer bbuf = ByteBuffer.allocate(partSize);
        bbuf.put(bytes);
        bbuf.flip();    //写/读模式切换

        //使用CharBuffer,将ByteBuffer中的内容
        CharBuffer cbuf = CharBuffer.allocate(partSize);
        charset.newDecoder().decode(bbuf, cbuf, true);
        cbuf.flip();

        //打印读取到的内容
        char[] chars = new char[cbuf.length()];
        if (cbuf.hasRemaining()) {
            cbuf.get(chars);
            System.out.println("read result: " + new String(chars));    //指定了长度为11,只读取到3个中文字符
        }

        //打印ByteBuffer中的剩余内容
        System.out.printf("remaining in ByteBuffer: %d\n", (bbuf.limit() - bbuf.position()));   //读取了3个中文字符,ByteBuffer中还剩下2个字节
    }
}
