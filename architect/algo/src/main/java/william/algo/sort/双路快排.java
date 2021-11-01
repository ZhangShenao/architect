package william.algo.sort;

import java.util.Arrays;

/**
 * @Author zhangshenao
 * @Date 2021-11-01
 * @Description
 */
public class 双路快排 {
    public static void doubleQuickSort(int[] arr) {
        //边界
        if (arr == null || arr.length == 0) {
            return;
        }

        //递归
        quickSort(arr, 0, arr.length - 1);

    }

    //对数组arr的[start,end]区间进行快速排序
    private static void quickSort(int[] arr, int start, int end) {
        if (start >= end) {  //递归退出条件
            return;
        }

        //递归做数组的左右两部分进行快排
        int p = partition(arr, start, end);
        quickSort(arr, start, p - 1);
        quickSort(arr, p + 1, end);
    }

    //对数组arr的[start,end]区间进行partition操作,返回partition下标
    private static int partition(int[] arr, int start, int end) {
        //固定选择第一个元素作为基准值
        int pivot = arr[start];

        //双路指针
        int i = start + 1;
        int j = end;

        while (true) {
            while (arr[i] < pivot && i <= end) {    //找到左边第一个比基准值大的元素
                ++i;
            }
            while (arr[j] > pivot && j >= start) {  //找到右边第一个比及职责小的元素
                --j;
            }
            if (i > j) {
                break;
            }
            int tmp = arr[i];       //交换两个元素
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;
            j--;
        }

        int tmp = arr[start];   //j就是基准值的位置,交换元素s
        arr[start] = arr[j];
        arr[j] = tmp;
        return j;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {-100, 9, 101, 900, -99324, 21342, 2334, -24892, 32582};
        doubleQuickSort(arr);
        System.err.println(Arrays.toString(arr));
    }
}
