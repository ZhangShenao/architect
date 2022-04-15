package william.interview.algo.sort;

import william.interview.algo.utils.ArrayGenerator;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {
    public static void sort(int[] arr) {
        //边界条件
        if (arr == null || arr.length <= 0) {
            return;
        }

        mergeSort(arr, 0, arr.length - 1);
    }

    //对数组arr[start,end]区间进行归并排序
    private static void mergeSort(int[] arr, int start, int end) {
        //递归退出条件
        if (end - start <= 1) {
            return;
        }

        int mid = (start + end) / 2;

        //分治:对数组左、右两部分分别进行归并排序
        mergeSort(arr, start, mid);
        mergeSort(arr, mid + 1, end);

        //对两部分进行merge
        merge(arr, start, mid, end);
    }

    //对数组arr的[leftStart,leftEnd]和[leftEnd+1,rightEnd]两部分进行merge
    private static void merge(int[] arr, int leftStart, int leftEnd, int rightEnd) {
        //申请新数组
        int[] tmp = new int[rightEnd - leftStart + 1];

        //按照顺序merge元素
        int l = leftStart;
        int r = leftEnd + 1;
        int i = 0;

        while (l <= leftEnd && r <= rightEnd) {
            if (arr[r] < arr[l]) {
                tmp[i] = arr[r++];
            } else {
                tmp[i] = arr[l++];
            }
            i++;
        }

        //处理剩余元素
        while (l <= leftEnd) {
            tmp[i] = arr[l++];
            i++;
        }

        while (r <= rightEnd) {
            tmp[i] = arr[r++];
            i++;
        }

        //将临时数组写回原数组
        for (int j = 0;j < tmp.length;j++){
            arr[leftStart + j] = tmp[j];
        }
    }

    public static void main(String[] args) {
        int[] arr = ArrayGenerator.genRandomIntegerArr(20, -1000, 1000);
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
