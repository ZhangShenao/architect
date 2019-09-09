package william.jvm.allocation;

import static william.jvm.allocation.EdenAllocation._1MB;

/**
 * @Author zhangshenao
 * @Date 2019-09-09
 * @Description 对象动态年龄判定:虚拟机并不要求对象年龄必须达到MaxTenuringThreshold才进入到老年代,
 * 如果Survivor区中相同年龄的所有对象大小的总和大于Survivor区的一半,年龄大于等于该年龄的对象就可以直接进入老年代,
 * 无需等到MaxTenuringThreshold的年龄要求
 *
 *  -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15
 */
public class DynamicTenuringThreshold {
    public static void main(String[] args) {
        byte[] allocation1,allocation2,allocation3,allocation4;

        allocation1 = new byte[_1MB / 4];
        //allocation1+allocation2的空间大于Survivor区的一半
        allocation2 = new byte[_1MB / 4];


        allocation3 = new byte[_1MB * 4];
        allocation4 = new byte[_1MB * 4];
        allocation4 = null;
        allocation4 = new byte[_1MB * 4];
    }
}
