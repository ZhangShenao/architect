package william.netty.nio;

import org.junit.Test;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.security.SecureRandom;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/21 15:02
 * @Description:
 */
public class NioDemo {
    @Test
    public void testIntBuffer() {
        IntBuffer buffer = IntBuffer.allocate(10);
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put(random.nextInt(20));
        }
        //读、写模式切换，要调用flip()方法
        buffer.flip();

        while (buffer.hasRemaining()) {
            System.err.println(buffer.get());
        }
    }

    @Test
    public void testReadFromChannel() throws Exception {
        FileInputStream in = new FileInputStream("/Users/william/Desktop/note.md");
        //通过Stream获取Channel
        FileChannel channel = in.getChannel();

        //从Channel中读取，一定要通过Buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);

        //读、写模式切换，要调用flip()方法
        buffer.flip();

        while (buffer.hasRemaining()) {
            byte b = buffer.get();
            System.err.print((char) b);
        }
        System.err.println();
        in.close();
    }

    @Test
    public void testWriteToChannel() throws Exception {
        FileOutputStream out = new FileOutputStream("/Users/william/Desktop/test.txt");
        FileChannel channel = out.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        byte[] bytes = "Hello World".getBytes();
        for (int i = 0, len = bytes.length; i < len; i++) {
            buffer.put(bytes[i]);
        }
        buffer.flip();
        channel.write(buffer);
        out.close();
    }

    @Test
    public void testPosition(){
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.err.println("init limit: " + buffer.limit()); //10

        for (int i = 0;i < 5;i++){
            buffer.put((byte)i);
        }
        System.err.println("after put,limit: " + buffer.limit());   //10

        buffer.flip();
        System.err.println("after flip,limit: " + buffer.limit());   //5
        System.err.println("after flip,position: " + buffer.position()); //0

        while (buffer.hasRemaining()){
            System.err.println("position: " + buffer.position());   //0~4
            System.err.println("limit: " + buffer.limit());         //5

            buffer.get();
        }
    }
}
