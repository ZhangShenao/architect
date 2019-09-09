package william.algo.sort;

import org.junit.Test;
import william.algorithm.util.ArrayUtils;

import java.util.Arrays;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/2 17:48
 * @Description:
 */
public class TestSorter {
    @Test
    public void testSorter() {
        Sorter sorter = new QuickSorter();
        int[] arr = ArrayUtils.generateRandomArray(10, 1, 100);
        sorter.sort(arr);
        System.err.println(Arrays.toString(arr));
    }

    @Test
    public void testHeapSorter() {
        Sorter sorter = new HeapSorter();
        int[] arr = ArrayUtils.generateRandomArray(10, 1, 100);
        sorter.sort(arr);
        System.err.println(Arrays.toString(arr));
    }
}
