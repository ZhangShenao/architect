package william.ds.queue;

import org.junit.Test;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/2 10:27
 * @Description:
 */
public class TestQueue {
    @Test
    public void testQueue() {
        Queue<Integer> queue = new LinkedQueue<>();
        for (int i = 0; i < 100; i++) {
            queue.offer((i + 1));
        }
        while (!queue.isEmpty()){
            System.err.println("data: " + queue.poll() + ", size: " + queue.size());
        }
    }
}
