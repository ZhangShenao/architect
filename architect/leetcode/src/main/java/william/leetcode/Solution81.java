package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/18 11:54
 * @Description:https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 */
public class Solution81 {
    public static void main(String[] args) {
        Solution81 s = new Solution81();
        int[] nums = {2, 5, 6, 0, 0, 1, 2};
        System.err.println(s.search(nums, 3));
    }

    public boolean search(int[] nums, int target) {
        return search(nums, 0, nums.length - 1, target) >= 0;
    }

    private int search(int[] nums, int start, int end, int target) {
        if (start > end) {
            return -1;
        }
        int mid = start + (end - start) / 2;
        if (target == nums[mid]) {
            return mid;
        }

        int idxL = -1;
        int idxR = -1;

        //中间值大于目标值,分别在左、右两边找到第一个小于中间值的元素
        if (nums[mid] > target) {
            int i = mid;
            while (i >= start && nums[i] >= nums[mid]) {
                --i;
            }
            if (i >= start && i < mid) {
                idxL = search(nums, start, i, target);
            }
            i = mid;
            while (i <= end && nums[i] >= nums[mid]) {
                ++i;
            }
            if (i <= end && i > mid) {
                idxR = search(nums, i, end, target);
            }
        }

        //中间值小于目标值,分别在左、右两边找到第一个大于中间值的元素
        if (nums[mid] < target) {
            int i = mid;
            while (i >= start && nums[i] <= nums[mid]) {
                --i;
            }
            if (i >= start && i < mid) {
                idxL = search(nums, start, i, target);
            }
            i = mid;
            while (i <= end && nums[i] <= nums[mid]) {
                ++i;
            }
            if (i <= end && i > mid) {
                idxR = search(nums, i, end, target);
            }
        }


        if (idxL >= 0) {
            return idxL;
        }
        if (idxR >= 0) {
            return idxR;
        }
        return -1;
    }
}
