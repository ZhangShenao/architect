package william.leetcode.sort;

import java.util.Arrays;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/14 上午10:49
 * <p>
 * 快速排序
 * 时间复杂度O(N*longN)
 * 空间复杂度O(1)
 */
public class 快速排序 {
    public static void sort(int[] arr) {
        //边界条件
        if (arr == null || arr.length == 0) {
            return;
        }

        quickSort(arr, 0, arr.length - 1);
    }

    //对数组arr的[start,end]范围进行快速排序
    //递归实现
    private static void quickSort(int[] arr, int start, int end) {
        //递归终止条件
        if (start >= end) {
            return;
        }

        //找到基准位置pivot
        int pivot = partition(arr, start, end);

        //递归:对数组基准位置的左、右两部分分别进行快速排序
        quickSort(arr, start, pivot - 1);
        quickSort(arr, pivot + 1, end);
    }

    //对数组arr的[start,end]范围进行partition操作,返回基准值下标
    private static int partition(int[] arr, int start, int end) {
        //默认选取第一个元素作为基准值
        int pivot = arr[start];

        //定义左、右指针
        int l = start;
        int r = end;

        //遍历数组,找到左边第一个大于基准值的元素,和右边第一个小于基准值的元素,进行交换
        while (true) {
            while (l <= end && arr[l] <= pivot) {
                l++;
            }
            while (r >= 0 && arr[r] >= pivot) {
                r--;
            }
            if (l >= r) {   //如果l和r相遇,则终止遍历
                break;
            }

            //交换元素
            int tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;
        }

        //最终r所在的位置为最后一个<=基准值的元素,将start和r位置的元素交换
        arr[start] = arr[r];
        arr[r] = pivot;

        //r的位置即为partition的位置
        return r;
    }

    public static void main(String[] args) {
        int[] arr = ArrayGenerator.genRandomIntArr(20, -1000, 1000);
        System.out.println("原始数组");
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println("排序后数组");
        System.out.println(Arrays.toString(arr));
    }
}
