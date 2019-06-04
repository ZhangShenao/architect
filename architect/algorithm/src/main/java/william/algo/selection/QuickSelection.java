package william.algo.selection;

import william.algorithm.util.ArrayUtils;
import java.util.Arrays;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/4 10:40
 * @Description:快速查找算法,基于快速排序的partition思想,找到数组中第k小的元素,时间复杂度O(logn)
 */
public class QuickSelection {
    public static int quickSelection(int[] arr, int k) {
        return quickSelectionNonRecursive(arr, k, 0, arr.length - 1);
    }

    //以非递归的形式进行快速查找
    private static int quickSelectionNonRecursive(int[] arr, int k, int start, int end) {
        while (start < end){
            int pivotIdx = partition(arr, start, end);
            if (pivotIdx == k){
                return arr[k];
            }
            if (pivotIdx > k){
                end = pivotIdx - 1;
            }else{
                start = pivotIdx + 1;
            }
        }
        return arr[start];
    }

    //在数组arr的[start,end]范围内,递归查找第k小的元素
    private static int quickSelectionRecursive(int[] arr, int k, int start, int end) {
        //递归退出条件
        if (start == end) {
            return arr[start];
        }

        //进行partition操作
        int pivotIdx = partition(arr, start, end);

        //根据pivotIdx和k的大小,决定在左/右部分继续查找
        if (pivotIdx == k) {
            return arr[k];
        }
        if (pivotIdx > k) {
            return quickSelectionRecursive(arr, k, start, pivotIdx - 1);
        }
        return quickSelectionRecursive(arr, k, pivotIdx + 1, end);
    }

    //对数组arr的[start,end]范围进行partition操作
    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[start];

        //l指向第一个大于等于pivot的元素
        int l = start + 1;
        for (int r = start + 1; r <= end; r++) {
            if (arr[r] <= pivot) {
                ArrayUtils.swapArrayElement(arr, l, r);
                ++l;
            }
        }

        //pivot索引为l - 1
        ArrayUtils.swapArrayElement(arr, start, l - 1);
        return l - 1;
    }

    public static void main(String[] args) {
        int[] arr = ArrayUtils.generateRandomArray(10, -100, 100);
        int k = 5;
        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);
        System.err.println(copy[k]);
        System.err.println(quickSelection(arr, k));
    }
}
