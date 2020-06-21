package william.jvm.objectheader;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Author zhangshenao
 * @Date 2020-06-18
 * @Description 查看对象各部分(对象头 + 实例数据 + Padding)所占内存空间的大小
 */
public class ShowObjectSize {
    public static void main(String[] args) {
        //Object对象
        ClassLayout o1 = ClassLayout.parseInstance(new Object());
        System.err.println(o1.toPrintable());
        System.err.println();

        //数组对象
        int[] arr = new int[4];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        ClassLayout o2 = ClassLayout.parseInstance(arr);
        System.err.println(o2.toPrintable());
        System.err.println();

        //自定义对象
        ClassLayout o3 = ClassLayout.parseInstance(new A());
        System.err.println(o3.toPrintable());
        System.err.println();

    }

    // ‐XX:+UseCompressedOops 默认开启的压缩所有指针
    // ‐XX:+UseCompressedClassPointers 默认开启的压缩对象头里的类型指针Klass Pointer
    // Oops : Ordinary Object Pointers
    public static class A {
        //8B mark word
        //4B Klass Pointer 如果关闭压缩‐XX:‐UseCompressedClassPointers或‐XX:‐UseCompressedOops，则占用8B

        private int id; //4B

        private String name;    //4B 如果关闭压缩‐XX:‐UseCompressedOops，则占用8B

        private byte b; //1B

        private Object o;   //4B 如果关闭压缩‐XX:‐UseCompressedOops，则占用8B
    }
}
