package william.algo.sort;

import java.util.Arrays;

import william.algo.utils.ArrayUtils;

/**
 * @Author zhangshenao
 * @Date 2021-04-18
 * @Description 快速排序
 * 时间复杂度：最好=O(n*logn) 最坏=O(n^2) 平均=O(n*logn) 时间复杂度的大小取决于基准值的选取，合理选取基准值可以将最坏时间复杂度出现的概率降到很低
 * 是原地排序，空间复杂度=O(1)
 * 不是稳定排序，因为交换操作会改变相同元素的初始顺序
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = ArrayUtils.genRandomArr(10, -100, 100);
        System.out.println("原始数组: " + Arrays.toString(arr));
        sort(arr);
        System.out.println("排序后数组: " + Arrays.toString(arr));
    }

    private static void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int arr[], int l, int r) {
        //递归终止条件
        if (l >= r) {
            return;
        }
        int p = partition(arr, l, r);
        quickSort(arr, l, p - 1);
        quickSort(arr, p + 1, r);
    }

    /**
     * 对数组进行partition，将数组分成小于基准值、基准值和大于基准值三部分
     *
     * @param arr 元素数组
     * @param l 起始下标
     * @param r 终止下标
     * @return 基准值下标
     */
    private static int partition(int[] arr, int l, int r) {
        //固定选取末尾元素作为基准值
        int p = arr[r];

        //将数组分为已处理和未处理两个部分，每次拿到未处理部分的第一个元素，与基准值比较，如果该元素大于基准值，则将改元素与已处理部分的最后一个元素进行交换
        int i = l;  //arr[l,i - 1]部分都是小于基准值的
        int j = l; //j表示未处理区间的第一个元素

        while (j < r) {
            if (arr[j] < p) {
                ArrayUtils.swap(arr, i++, j);
            }
            ++j;
        }

        ArrayUtils.swap(arr, i, r);
        return i;
    }
}
