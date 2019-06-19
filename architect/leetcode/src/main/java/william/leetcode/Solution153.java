package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/19 08:15
 * @Description:https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 */
public class Solution153 {
    public static void main(String[] args) {
        Solution153 s = new Solution153();
        int nums[] = {3, 1, 3};
        System.err.println(s.findMin(nums));
    }

    public int findMin(int[] nums) {
        return findMin(nums, 0, nums.length - 1);
    }

    private int findMin(int nums[], int start, int end) {
        if (start >= end) {
            return nums[start];
        }

        //如果nums[start] < nums[end],表示这[start,end]部分数组是有序的
        if (nums[start] < nums[end]) {
            return nums[start];
        }

        //否则说明数组是旋转过的,则分别在左、右两部分找到最小的
        int mid = start + (end - start) / 2;
        int minL = findMin(nums, start, mid);
        int minR = findMin(nums, mid + 1, end);
        return Math.min(minL, minR);
    }
}
