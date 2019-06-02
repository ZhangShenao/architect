package william.algo;

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
        Sorter sorter = new MergeSorter();
        int[] arr = ArrayUtils.generateRandomArray(10, 1, 100);
        sorter.sort(arr);
        System.err.println(Arrays.toString(arr));
    }
}