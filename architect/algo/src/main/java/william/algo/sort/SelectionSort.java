package william.algo.sort;

import java.util.Arrays;

import william.algo.utils.ArrayUtils;

/**
 * @Author zhangshenao
 * @Date 2021-04-18
 * @Description 选择排序
 * 时间复杂度： 最好、最坏、平均情况时间复杂度都=O(n^2)
 * 是原地排序，空间复杂度=O(1)
 * 不是稳定排序
 * 选择排序基本只用于理论分析，不会在实际业务中使用
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = ArrayUtils.genRandomArr(10, -100, 100);
        System.out.println("原始数组: " + Arrays.toString(arr));
        sort(arr);
        System.out.println("排序后数组: " + Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            ArrayUtils.swap(arr, i, minIdx);
        }
    }
}
