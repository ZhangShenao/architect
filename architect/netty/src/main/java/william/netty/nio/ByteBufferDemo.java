package william.netty.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/22 13:19
 * @Description:
 */
public class ByteBufferDemo {
    //类型化的getxxx()和putxxx()方法
    @Test
    public void test1() {
        ByteBuffer buffer = ByteBuffer.allocate(512);
        buffer.putInt(1);
        buffer.putLong(100L);
        buffer.putChar('a');
        buffer.put((byte) 1);

        buffer.flip();

        //读取的时候，必须按照写入的顺序，因为不同数据类型所占据的数组长度是不同的
        System.err.println(buffer.getInt());
        System.err.println(buffer.getLong());
        System.err.println(buffer.getChar());
        System.err.println(buffer.get());
    }

    //分片
    @Test
    public void testSlice() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0, cap = buffer.capacity(); i < cap; i++) {
            buffer.put((byte) i);
        }

        buffer.position(2).limit(6);
        //创建一个Slice，范围是源Buffer的[position,limit)。两个Buffer共享数据，但是各自的position、limit和capacity是各自独立的
        ByteBuffer sliced = buffer.slice();

        //修改Slice的数据，源Buffer也会受到影响
        for (int i = 0, cap = sliced.capacity(); i < cap; i++) {
            byte b = sliced.get(i);
            sliced.put(i, (byte) (b * 2));
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());
        while (buffer.hasRemaining()) {
            System.err.println(buffer.get());
        }
    }

    //只读Buffer
    @Test
    public void testReadonly() {
        ByteBuffer buffer = ByteBuffer.allocate(20);
        for (int i = 0, cap = buffer.capacity(); i < cap; i++) {
            buffer.put((byte) i);
        }

        buffer.flip();
        //获取只读Buffer。可以随时将一个读写Buffer转换成只读Buffer
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        while (readOnlyBuffer.hasRemaining()){
            System.err.println(readOnlyBuffer.get());
        }
    }
}
