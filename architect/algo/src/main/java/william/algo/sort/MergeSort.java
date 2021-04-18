package william.algo.sort;

import java.util.Arrays;

import william.algo.utils.ArrayUtils;

/**
 * @Author zhangshenao
 * @Date 2021-04-18
 * @Description 归并排序
 * 如果要排序一个数组，我们先把数组从中间分成前后两部分，然后对前后两部分分别排序，再将排好序的两部分合并在一起，这样整个数组就都有序了
 * 时间复杂度：最好、最好、平均情况时间复杂度都为O(n*logn)
 * 是稳定排序
 * 不是原地排序，空间复杂度为O(n)
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = ArrayUtils.genRandomArr(10, -100, 100);
        System.out.println("原始数组: " + Arrays.toString(arr));
        sort(arr);
        System.out.println("排序后数组: " + Arrays.toString(arr));
    }

    private static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        mergeSort(arr, 0, arr.length - 1);
    }

    /**
     * 对数组arr的[start,end]区间进行归并排序
     *
     * @param arr 待排序数组
     * @param start 起始下标
     * @param end 终止下标
     */
    private static void mergeSort(int[] arr, int start, int end) {
        //递归终止条件
        if (start >= end) {
            return;
        }

        //递归对区间的左、右两部分分别进行递归排序
        int mid = (start + end) / 2;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid + 1, end);

        //对排好序的两部分数组进行合并
        int len = end - start + 1;
        int[] tmp = new int[len];
        int i = 0;
        int l = start;
        int r = mid + 1;

        while (l <= mid && r <= end && i < len) {
            if (arr[l] <= arr[r]) {
                tmp[i] = arr[l];
                ++i;
                ++l;
            } else {
                tmp[i] = arr[r];
                ++i;
                ++r;
            }
        }

        while (l <= mid && i < len) {
            tmp[i] = arr[l];
            ++i;
            ++l;
        }

        while (r <= end && i < len) {
            tmp[i] = arr[r];
            ++i;
            ++r;
        }

        //将排好序的数组拷贝回原数组
        for (int j = 0; j < len; j++) {
            arr[start + j] = tmp[j];
        }
    }
}
