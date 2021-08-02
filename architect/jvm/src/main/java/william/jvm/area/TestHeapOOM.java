package william.jvm.area;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangshenao
 * @Date 2020-06-15
 * @Description 测试堆OOM
 * -Xmx1G -Xms1G
 */
public class TestHeapOOM {
    private static final int ONE_M = 1024 * 1024;

    public static void main(String[] args) throws InterruptedException {
        List<byte[]> arrs = new ArrayList<>();

        while (true) {
            arrs.add(new byte[ONE_M]);
            Thread.sleep(100L);
        }
    }
}
