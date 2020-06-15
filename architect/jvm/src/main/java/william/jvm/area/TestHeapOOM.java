package william.jvm.area;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangshenao
 * @Date 2020-06-15
 * @Description 测试堆OOM
 */
public class TestHeapOOM {
    public static void main(String[] args) throws InterruptedException {
        List<int[]> arrs = new ArrayList<>();

        while (true) {
            arrs.add(new int[100000]);
            Thread.sleep(100L);
        }
    }
}
