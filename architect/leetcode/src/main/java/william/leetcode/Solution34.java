package william.leetcode;

import java.util.Arrays;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/18 12:04
 * @Description:https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class Solution34 {
    public static void main(String[] args) {
        Solution34 s = new Solution34();
        int[] nums = {5, 7, 7, 8, 8, 10};
        System.err.println(Arrays.toString(s.searchRange(nums,6)));
    }

    public int[] searchRange(int[] nums, int target) {
        return search(nums, 0, nums.length - 1, target);
    }

    private int[] search(int[] nums, int start, int end, int target) {
        if (start > end) {
            return new int[]{-1, -1};
        }

        //处理不相等的情况
        int mid = start + (end - start) / 2;
        if (nums[mid] > target) {
            return search(nums, start, mid - 1, target);
        }
        if (nums[mid] < target) {
            return search(nums, mid + 1, end, target);
        }

        //处理相等的情况
        int[] arrL = search(nums, start, mid - 1, target);
        int[] arrR = search(nums, mid + 1, end, target);

        int[] arr = new int[2];
        arr[0] = arrL[0] >= 0 ? arrL[0] : mid;
        arr[1] = arrR[1] >= 0 ? arrR[1] : mid;

        return arr;
    }
}
