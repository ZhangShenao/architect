package william.algo.search;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import william.algo.utils.ArrayUtils;

/**
 * @Author zhangshenao
 * @Date 2021-04-21
 * @Description 二分查找
 * 针对有序数据集合，时间复杂度为O(logn)
 */
public class BinarySearch {
    public static void main(String[] args) {
        int size = 20;
        int[] arr = ArrayUtils.genRandomArr(size, -100, 100);
        Arrays.sort(arr);
        int idx = ThreadLocalRandom.current().nextInt(0, size);
        int target = arr[idx];
        System.out.println("待查找元素: " + target + ", 待查找元素的下标: " + idx);
        System.out.println("普通二分查找结果: " + binarySearch(arr, target));
        System.out.println("递归二分查找结果: " + binarySearchRecursively(arr, 0, size - 1, target));
    }

    /**
     * 二分查找
     *
     * @param arr 待查找数组
     * @param target 目标元素
     * @return 目标元素所在的下标。如果未找到则返回-1
     */
    private static int binarySearch(int[] arr, int target) {
        if (arr == null || arr.length < 1) {
            return -1;
        }

        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 基于递归实现二分查找
     *
     * @param arr 待查找的数组
     * @param low 起始下标
     * @param high 终止下标
     * @param target 目标元素
     * @return 目标元素所在的下标。如果未找到则返回-1
     */
    private static int binarySearchRecursively(int[] arr, int low, int high, int target) {
        //递归终止条件
        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (arr[mid] == target) {
            return mid;
        }
        if (arr[mid] > target) {
            return binarySearchRecursively(arr, low, mid - 1, target);
        } else {
            return binarySearchRecursively(arr, mid + 1, high, target);
        }
    }
}
