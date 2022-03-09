package william.concurrent.unsafe;


import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/27 18:24
 * @Description:
 */
public class UnsafeTest {
    public static void main(String[] args) throws Exception {
//        //获取Unsafe实例
//        Field field = Unsafe.class.getDeclaredField("theUnsafe");
//        field.setAccessible(true);
//        Unsafe unsafe = (Unsafe) field.get(null);
//        System.err.println("Unsafe: " + unsafe);
//
//        byte[] bytes = new byte[10];
//        System.err.println("array: " + Arrays.toString(bytes));
//
//        //通过Unsafe获取初始偏移量
//        int baseOffset = unsafe.arrayBaseOffset(byte[].class);
//        System.err.println("baseOffset: " + baseOffset);
//
//        //通过Unsafe和偏移量,修改数组元素
//        unsafe.putByte(bytes,baseOffset,(byte)1);
//        unsafe.putByte(bytes,baseOffset + 5,(byte)5);
//        System.err.println("array: " + Arrays.toString(bytes));
    }
}
