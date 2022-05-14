package william.concurrent.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/14 下午1:44
 */
public class UnsafeFactory {
    public static Unsafe getInstance() throws Exception {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        return (Unsafe) field.get(null);
    }
}
