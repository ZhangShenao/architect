package william.interview.buffer;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

import static java.nio.file.StandardOpenOption.READ;

/**
 * Buffer 缓冲区
 * Buffer本质上是对操作的排队,用于解决上下游处理速度不匹配的问题
 * Buffer有助于实现Batch
 */
public class BufferOperations {
    //ByteBuffer的常用操作
    @Test
    public void testByteBufferOperations() {
        ByteBuffer buf = ByteBuffer.allocate(10);  //分配capacity=10的Buffer
        buf.put((byte) 97);
        buf.put((byte) 98);
        buf.put((byte) 99);
        System.out.printf("after write: position=%d\tlimit=%d\tcapacity=%d\n", buf.position(), buf.limit(), buf.capacity());

        //flip,将ByteBuffer从写模式切换到读模式
        buf.flip();
        System.out.printf("after flip: position=%d\tlimit=%d\tcapacity=%d\n", buf.position(), buf.limit(), buf.capacity());

        //从ByteBuffer中读取数据
        System.out.println(buf.get());
        System.out.println(buf.get());
        System.out.printf("after read: position=%d\tlimit=%d\tcapacity=%d\n", buf.position(), buf.limit(), buf.capacity());

        //rewind,重新读取
        buf.rewind();
        System.out.printf("after rewind: position=%d\tlimit=%d\tcapacity=%d\n", buf.position(), buf.limit(), buf.capacity());
        System.out.println(buf.get());
        System.out.println(buf.get());

        //clear
        buf.clear();
        System.out.printf("after clear: position=%d\tlimit=%d\tcapacity=%d\n", buf.position(), buf.limit(), buf.capacity());
    }

    //基于Buffer写入
    @Test
    public void testWriteWithBuffer() {
        String fileName = "word.txt";
        int bufSize = 4 * 1024; //指定Buffer大小

        long start = System.currentTimeMillis();

        //使用BufferedOutputStream可以提升写性能
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileName), bufSize)) {
            for (int i = 0; i <= 10000000; i++) {
                for (int j = 0; j < 5; j++) {
                    int c = (97 + ThreadLocalRandom.current().nextInt(5));
                    out.write(c);
                }
                out.write(' ');
            }

            System.out.println("write costs: " + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //基于Buffer读取
    @Test
    public void testReadWithBuffer() {
        String fileName = "word.txt";
        int bufSize = 4 * 1024; //指定Buffer大小

        //执行读取的byte数组
        byte[] buf = new byte[bufSize];

        //使用BufferedInputStream可以提升写性能
        long start = System.currentTimeMillis();
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileName), bufSize)) {
            //将内容读取至byte数组中
            while (in.read(buf) != -1) {
                //读取byte数组内容
//                System.out.println(new String(buf));
            }

            System.out.println("read costs: " + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //使用标准NIO Channel
    @Test
    public void testReadByChannel() {
        String fileName = "word.txt";

        //创建ByteBuffer
        ByteBuffer buf = ByteBuffer.allocate(1024 * 8);

        long start = System.currentTimeMillis();
        //基于FileInputStream创建Channel
        try (FileChannel channel = new FileInputStream(fileName).getChannel()) {
            while (channel.read(buf) != -1) {
                //flip将ByteBuffer从写模式切换成读模式
                buf.flip();

//                System.out.println(new String(buf.array()));
            }

            System.out.println("read costs: " + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //异步读取
    @Test
    public void testReadAsync() {
        String fileName = "word.txt";
        ByteBuffer buf = ByteBuffer.allocate(8 * 1024);
        try (AsynchronousFileChannel channel = AsynchronousFileChannel.open(Path.of(fileName), READ)) {
            Future<Integer> future = channel.read(buf, 0L);
            System.out.println(future.get());   //获取异步读写结果
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
