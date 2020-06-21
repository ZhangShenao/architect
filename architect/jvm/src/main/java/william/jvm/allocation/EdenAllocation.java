package william.jvm.allocation;

/**
 * @Author zhangshenao
 * @Date 2019-09-09
 * @Description 大多数情况下, 对象被分配在新生代的Eden区
 * <p>
 * -XX:+PrintGCDetails -XX:SurvivorRatio=8
 */
public class EdenAllocation {
    public static final int _1MB = 1014 * 1024;

    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;

        allocation1 = new byte[6 * _1MB];
//        allocation2 = new byte[2 * _1MB];
//        allocation3 = new byte[2 * _1MB];

        //此处会发生一次MinorGC
//        allocation4 = new byte[4 * _1MB];
    }
}
