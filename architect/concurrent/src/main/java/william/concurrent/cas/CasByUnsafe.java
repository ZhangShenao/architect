package william.concurrent.cas;

import sun.misc.Unsafe;
import william.concurrent.unsafe.UnsafeFactory;

import java.lang.reflect.Field;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/14 下午1:38
 * <p>
 * 基于底层的Unsafe实现CAS操作
 */
public class CasByUnsafe {
    public static void main(String[] args) throws Exception {
        Counter counter = new Counter();

        //获取Unsafe对象
        Unsafe unsafe = UnsafeFactory.getInstance();

        //获取字段偏移量
        Field field = counter.getClass().getField("value");
        long offset = unsafe.objectFieldOffset(field);

        //使用Unsafe提供的CAS操作
        boolean success = unsafe.compareAndSwapInt(counter, offset, 0, 1);
        System.out.println("update counter value to 1, result: " + success + ". current value is: " + counter.value);

        success = unsafe.compareAndSwapInt(counter, offset, 1, 2);
        System.out.println("update counter value to 2, result: " + success + ". current value is: " + counter.value);

        success = unsafe.compareAndSwapInt(counter, offset, 1, 3);
        System.out.println("update counter value to 3, result: " + success + ". current value is: " + counter.value);


    }

    static class Counter {
        int value;
    }
}
