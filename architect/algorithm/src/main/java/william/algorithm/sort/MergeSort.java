package william.algorithm.sort;

import william.algorithm.util.RandomArrayGenerator;

import java.util.Arrays;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/8 12:52
 * @Description:归并排序，时间复杂度所有情况下均为O(n*logn),空间复杂度O(n)，在处理近乎有序的数据时效率很高
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] array = RandomArrayGenerator.generateRandomArray(10, -100, 100);
        System.err.println(Arrays.toString(array));
        mergeSort(array);
        System.err.println(Arrays.toString(array));
    }

    public static void mergeSort(int[] arr) {
        doMergeSort(arr, 0, arr.length - 1);
    }

    //将数组分为左右两部分，分别进行归并排序，然后将排序后的两个有序数组合并成一个有序数组
    private static void doMergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + ((right - left) >> 1);   //使用右移运算代替除法，提升性能，且避免直接求和造成溢出
        doMergeSort(arr, left, mid);
        doMergeSort(arr, mid + 1, right);

        //优化点——如果右边数组的第一个元素大于等于左边数组的最后一个元素，说明两部分已经完全有序，可以结束归并
        if (arr[mid + 1] >= arr[mid]) {
            return;
        }
        //左右两部分都有序后，将两部分合并成同一个数组
        doMerge(arr, left, mid, right);
    }

    //merge操作，将两个分别有序的数组合并成一个有序的数组
    private static void doMerge(int[] arr, int leftStart, int leftEnd, int rightEnd) {
        int rightStart = leftEnd + 1;
        int size = (rightEnd - leftStart) + 1;
        int[] tmpArr = new int[size];
        int tmpIdx = 0;

        int i = leftStart;
        int j = rightStart;

        while (i <= leftEnd && j <= rightEnd) {
            //如果左右两个元素相等，优先放左边的，保证排序的稳定性
            if (arr[i] <= arr[j]) {
                tmpArr[tmpIdx++] = arr[i++];
            } else {
                tmpArr[tmpIdx++] = arr[j++];
            }
        }

        while (i <= leftEnd) {
            tmpArr[tmpIdx++] = arr[i++];
        }

        while (j <= rightEnd) {
            tmpArr[tmpIdx++] = arr[j++];
        }

        for (int k = 0; k < size; k++) {
            arr[k + leftStart] = tmpArr[k];



        }

    }
}
