package william.algo.sort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import william.algo.utils.ArrayUtils;

/**
 * @Author zhangshenao
 * @Date 2021-04-20
 * @Description 找到一个数组中第k小的元素(k从0开始)
 * 借助跨苏排序的思想
 */
public class FindKthSmallest {
    public static void main(String[] args) {
        int size = 10;
        int[] arr = ArrayUtils.genRandomArr(size, -100, 100);
        int k = ThreadLocalRandom.current().nextInt(0, size);
        System.out.println("原始数组: " + Arrays.toString(arr));
        System.out.println("找到数组中第" + k + "小的元素: " + findKthSmallest(arr, k));
        Arrays.sort(arr);
        System.out.println("排序后的数组: " + Arrays.toString(arr));
    }

    private static int findKthSmallest(int[] arr, int k) {
        return find(arr, 0, arr.length - 1, k);
    }

    /**
     * 在数组arr的[start,end]区间内，找到第k小的元素
     *
     * @param arr 目标数组
     * @param start 起始下标
     * @param end 终止下标
     * @return 找到的元素
     */
    private static int find(int[] arr, int start, int end, int k) {
        if (arr == null || arr.length < k) {
            throw new IllegalArgumentException();
        }
        //递归终止条件
        if (start >= end) {
            return arr[start];
        }

        //对数组进行partition操作，找到基准下标
        int p = partition(arr, start, end);

        //如果基准下标与目标下标相同,则直接返回
        if (p == k) {
            return arr[p];
        }

        //如果基准下标大于目标下标,则在小于基准值的区间查找
        if (p > k) {
            return find(arr, start, p - 1, k);
        }

        //如果基准下标小于目标下标,则在大于基准值的区间查找
        return find(arr, p + 1, end, k);
    }

    /**
     * 对数组进行partition操作,将整个数组划分为小于基准值、等于基准值和大于基准值的三个部分
     *
     * @param arr 目标数组
     * @param start 起始下标
     * @param end 终止下标
     * @return 基准下标
     */
    private static int partition(int[] arr, int start, int end) {
        //固定选取数组末尾元素作为基准值
        int p = arr[end];

        //将整个数组划分为已处理区间和未处理区间两个部分
        int i = start;  //[start,i-1]区间内的元素都小于基准值
        int j = i; //j指向未处理区间的第一个元素

        while (j <= end) {
            //如果未处理区间的当前元素小于基准值,则将当前元素与arr[i]处的元素进行交换
            if (arr[j] < p) {
                ArrayUtils.swap(arr, i++, j);
            }
            ++j;
        }

        //将基准下标与i下标处的元素交换,i就是基准下标
        ArrayUtils.swap(arr, i, end);
        return i;
    }
}
