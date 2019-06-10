package william.ds.heap;

import org.junit.Test;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/10 13:53
 * @Description:大顶堆测试
 */
public class TestMaxHeap {
    @Test
    public void testMaxHeap(){
        MaxHeap<Integer> heap = new MaxHeap<>(10);
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0;i < 10;i++){
            heap.insert(random.nextInt(-100,100));
        }
        System.err.println("size: " + heap.size());

        while (!heap.isEmpty()){
            System.err.print(" " + heap.extractMax() + " ");
        }
        System.err.println();
    }
}
