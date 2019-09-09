package william.enhance.search;

import william.utils.AlgorithmUtils;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/20 12:17
 * @Description:二分查找及其相关问题 前提:数组是有序的
 */
public class BinarySearch {
    public static void main(String[] args) {
        int n = 10;
        int min = 0;
        int max = 4;
        Integer[] arr = AlgorithmUtils.genRandomArray(n, 0, 4);
        Arrays.sort(arr);
        System.err.println(Arrays.toString(arr));
        int target = ThreadLocalRandom.current().nextInt(min, max + 1);
        System.err.println("Binary Search " + target + " : " + binarySearch(arr, target));
        System.err.println("floor " + target + " : " + floor(arr, target));
        System.err.println("ceil " + target + " : " + ceil(arr, target));
    }

    //在数组arr中查找第一个==target或最后一个<target的元素
    public static <T extends Comparable<T>> int floor(T[] arr, T target) {
        int l = 0;
        int r = arr.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid].compareTo(target) >= 0) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return (r < arr.length - 1 && arr[r + 1] == target ? r + 1 : r);
    }

    //在数组arr中查找最后一个个==target或第一个>target的元素
    public static <T extends Comparable<T>> int ceil(T[] arr, T target) {
        int l = 0;
        int r = arr.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid].compareTo(target) <= 0) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return r;
    }


    //普通二分查找
    public static <T extends Comparable<T>> int binarySearch(T[] arr, T target) {
        int l = 0;
        int r = arr.length - 1;

        //在arr[l,r]范围内查找target
        //l=r时也需要考虑
        while (l <= r) {
            int mid = l + (r - l) / 2;      //避免溢出
            if (arr[mid].compareTo(target) == 0) {
                return mid;
            }
            if (arr[mid].compareTo(target) > 0) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    //递归二分查找
    //在数组arr[l,r]范围内查找target
    public static <T extends Comparable<T>> int binarySearchRecursive(T[] arr, int l, int r, T target) {
        //递归退出条件
        if (l > r) {
            return -1;
        }

        int mid = l + (r - l) / 2;
        if (arr[mid].compareTo(target) == 0) {
            return mid;
        }

        if (arr[mid].compareTo(target) > 0) {
            return binarySearchRecursive(arr, l, mid - 1, target);
        }

        return binarySearchRecursive(arr, mid + 1, r, target);
    }
}
