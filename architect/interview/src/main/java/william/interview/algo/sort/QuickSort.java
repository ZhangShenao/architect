package william.interview.algo.sort;

import william.interview.algo.utils.ArrayGenerator;
import william.interview.algo.utils.Swap;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 0) {   //边界条件
            return;
        }

        quickSort(arr, 0, arr.length - 1);
    }

    //对数组arr的[start,end]区间进行快速排序
    private static void quickSort(int[] arr, int start, int end) {
        //递归终止条件
        if (start >= end) {
            return;
        }

        //选取基准值,将数组一分为二
        int pivot = partition(arr, start, end);

        //对左、右两部分分别进行快速排序
        quickSort(arr, start, pivot - 1);
        quickSort(arr, pivot + 1, end);
    }

    //对数组arr的[start,end]区间内进行partition操作
    private static int partition(int[] arr, int start, int end) {
        //固定选择第一个元素作为基准值
        int pivot = arr[start];

        //双路指针
        int i = start + 1;
        int j = end;

        while (true) {
            while (i <= end && arr[i] < pivot) {    //找到左边第一个比基准值大的元素
                ++i;
            }
            while (j >= start && arr[j] > pivot) {  //找到右边第一个比基准值小的元素
                --j;
            }
            if (i >= j) {
                break;
            }

            //交换元素
            Swap.swap(arr, i, j);

            //进行下一轮
            i++;
            j--;
        }

        //最终j代表最后一个小于基准值的位置,i代表第一个大于基准值的位置。j就是基准值的位置,交换元素
        Swap.swap(arr, start, j);
        return j;
    }

    public static void main(String[] args) {
        int[] arr = ArrayGenerator.genRandomIntegerArr(20, -1000, 1000);
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
