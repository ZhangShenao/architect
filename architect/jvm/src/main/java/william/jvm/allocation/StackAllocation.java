package william.jvm.allocation;

/**
 * @Author zhangshenao
 * @Date 2020-06-20
 * @Description 栈上分配
 * 我们通过JVM内存分配可以知道JAVA中的对象都是在堆上进行分配，当对象没有被引用的时候，需要依靠GC进行回收内存。
 * 如果对象数量较多的时候,会给GC带来较大压力,也间接影响了应用的性能。
 * 为了减少临时对象在堆内分配的数量,JVM通过逃逸分析确定该对象不会被外部访问。
 * 如果不会逃逸可以将该对象在栈上分配内存,这样该对象所占用的内存空间就可以随栈帧出栈而销毁,就减轻了垃圾回收的压力。
 * <p>
 * 栈上分配依赖于逃逸分析和标量替换
 * 开启逃逸分析: -XX:+DoEscapeAnalysis JDK7之后默认开启
 * 开启标量替换: -XX:+EliminateAllocations JDK7之后默认开启
 *
 * -Xms15M -Xmx15M -XX:+PrintGCDetail
 */
public class StackAllocation {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 1000000; i++) {
            allocate();
        }

        System.err.println("cost: " + (System.currentTimeMillis() - start));
    }

    //该对象不会逃逸,因此可以进行栈上分配,几乎不会产生GC
    private static void allocate() {
        User u = new User();
        u.setId(1L);
        u.setName("user");
    }

}
