package william.leetcode.binarysearch;

/**
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/
 */
public class 在排序数组中查找元素的第一个和最后一个位置34 {
    public int[] searchRange(int[] nums, int target) {
        //边界条件
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        //采用二分查找
        int first = binarySearchFirstEquals(nums, target);
        int last = binarySearchLastEquals(nums, target);
        return new int[]{first, last};
    }

    //二分查找第一个等于target的元素
    private int binarySearchFirstEquals(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] >= target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        if (l >= 0 && l < nums.length && nums[l] == target) {
            return l;
        }
        return -1;
    }

    //二分查找最后一个等于target的元素
    private int binarySearchLastEquals(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        if (r >= 0 && r < nums.length && nums[r] == target) {
            return r;
        }
        return -1;
    }
}
