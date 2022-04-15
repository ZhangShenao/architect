package william.interview.algo.sort;

import william.interview.algo.utils.ArrayGenerator;
import william.interview.algo.utils.Swap;

import java.util.Arrays;

/**
 * 选择排序
 * 选择排序是非稳定的排序
 */
public class SelectionSort {
    public static void sort(int[] arr) {
        //边界条件
        if (arr == null || arr.length <= 0) {
            return;
        }

        //每次选出[0,i]范围内的最大值,与下标i处的元素交换
        for (int i = arr.length - 1; i > 0; i--) {
            //找到最大元素的下标
            int maxIdx = maxIdx(arr, 0, i);

            //交换当前下标i和最大下标处的元素
            Swap.swap(arr, i, maxIdx);
        }
    }

    private static int maxIdx(int[] arr, int start, int end) {
        int maxIdx = start;
        for (int i = start; i <= end; i++) {
            if (arr[i] > arr[maxIdx]) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    public static void main(String[] args) {
        int[] arr = ArrayGenerator.genRandomIntegerArr(20, -1000, 1000);
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
