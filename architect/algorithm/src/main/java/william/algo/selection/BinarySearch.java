package william.algo.selection;

import william.algorithm.util.ArrayUtils;
import java.util.Arrays;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/4 11:49
 * @Description:二分查找
 */
public class BinarySearch {
    public static int binarySelection(int[] arr, int target) {
        return binarySelectionRecursive(arr, target, 0, arr.length - 1);
    }

    private static int binarySelection(int[] arr, int target, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;    //避免start+end过大溢出
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    //二分查找的递归实现
    private static int binarySelectionRecursive(int[] arr, int target, int start, int end) {
        if (start > end){
            return -1;
        }
        int mid = start + (end - start) / 2;    //避免start+end过大溢出
        if (arr[mid] == target) {
            return mid;
        }
        if (arr[mid] > target) {
            end = mid - 1;
        } else {
            start = mid + 1;
        }
        return binarySelectionRecursive(arr, target, start, end);
    }

    public static void main(String[] args) {
        int[] arr = ArrayUtils.generateRandomArray(20, -100, 100);
        int target = arr[5];
        Arrays.sort(arr);
        System.err.println(binarySelection(arr,target));
    }
}
