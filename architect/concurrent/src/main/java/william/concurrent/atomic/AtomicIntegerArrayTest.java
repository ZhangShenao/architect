package william.concurrent.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/20 16:17
 * @Description:
 */
public class AtomicIntegerArrayTest {
    public static void main(String[] args) {
        int[] arr = new int[] {1,2};
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(arr);
        atomicIntegerArray.set(0,5);
        System.err.println(atomicIntegerArray.get(0));
        System.err.println(arr[0]);
    }
}
