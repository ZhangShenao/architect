package william.jvm.allocation;

import static william.jvm.allocation.EdenAllocation._1MB;

/**
 * @Author zhangshenao
 * @Date 2019-09-09
 * @Description 大对象直接进入老年代,通过-XX:PretenureSizeThreshold=3145728设置
 * -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
 */
public class DirectOldGen {
    public static void main(String[] args) {
        //大对象直接进入老年代
        byte[] allocation = new byte[4 * _1MB];
    }
}
