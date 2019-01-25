package william.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/21 15:14
 * @Description:
 */
public class AtomicIntegerTest {
    private static AtomicInteger i = new AtomicInteger(0);
    public static void main(String[] args) {
        increment();
        System.err.println(i.get());
    }

    private static void increment(){
        int value = AtomicIntegerTest.i.get();
        while (!(i.compareAndSet(value,value + 1))){}
    }
}
