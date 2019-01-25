package william.algorithm.search;

import william.algorithm.sort.HeapSort;
import william.algorithm.util.RandomArrayGenerator;
import java.util.Arrays;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/15 10:22
 * @Description:二分查找,前提是数组有序，时间复杂度O(logn)
 */
public class BinarySearch {
    //普通二分查找
    public static int binarySearch(int[] arr,int target){
        int start = 0;
        int end = arr.length - 1;
        while (start <= end){
            int mid = start + ((end - start) >> 1);
            if (target == arr[mid]){
                return mid;
            }
            if (target > arr[mid]){
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }

        return -1;
    }

    //递归的二分查找
    public static int binarySearchRecursively(int[] arr,int start,int end,int target){
        if (start > end){
            return -1;
        }
        int mid = start + ((end - start) >> 1);
        if (arr[mid] == target){
            return mid;
        }
        if (arr[mid] > target){
            return binarySearchRecursively(arr,start,mid - 1,target);
        }
        return binarySearchRecursively(arr,mid + 1,end,target);
    }

    public static void main(String[] args) {
        int[] arr = RandomArrayGenerator.generateRandomArray(20, -100, 100);
        System.err.println(Arrays.toString(arr));
        HeapSort.heapSort(arr);
        System.err.println(Arrays.toString(arr));
//        int target = arr[5];
        int target = 90;
//        System.err.println("index: " + binarySearch(arr,target));
        System.err.println("index: " + binarySearchRecursively(arr,0,19,target));
    }
}
