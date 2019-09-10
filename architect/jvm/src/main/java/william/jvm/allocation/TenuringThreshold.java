package william.jvm.allocation;

import static william.jvm.allocation.EdenAllocation._1MB;

/**
 * @Author zhangshenao
 * @Date 2019-09-09
 * @Description 长期存活的对象(默认经历了15次MinorGC)进入老年代,通过-XX:MaxTenuringThreshold=1设置
 *
 *  -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1
 */
public class TenuringThreshold {
    public static void main(String[] args) {
        byte[] allocation1,allocation2,allocation3;

        allocation1 = new byte[_1MB / 4];

        //什么时候进入老年代取决于-XX:MaxTenuringThreshold的值
        allocation2 = new byte[_1MB * 4];
        allocation3 = new byte[_1MB * 4];
        allocation3 = null;

        allocation3 = new byte[_1MB * 4];
    }
}
