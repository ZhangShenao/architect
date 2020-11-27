package william.jvm.layout;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Author zhangshenao
 * @Date 2020-06-18
 * @Description 对象内存布局 = 对象头 + 实例数据 + 对齐填充
 * 对象头 = MarkWord + 类型指针 + 数组长度(仅数组类型采用)
 */
public class ShowObjectLayout {
    public static void main(String[] args) {
        //Object对象
        ClassLayout l1 = ClassLayout.parseInstance(new Object());
        System.err.println("Object对象");
        System.err.println(l1.toPrintable());
        System.err.println();

        //数组对象
        int[] arr = new int[4];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        ClassLayout l2 = ClassLayout.parseInstance(arr);
        System.err.println("数组对象");
        System.err.println(l2.toPrintable());
        System.err.println();

        //自定义对象
        ClassLayout l3 = ClassLayout.parseInstance(new A());
        System.err.println("自定义对象");
        System.err.println(l3.toPrintable());
        System.err.println();

    }

    // -XX:+UseCompressedOops 默认开启的压缩所有指针
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
