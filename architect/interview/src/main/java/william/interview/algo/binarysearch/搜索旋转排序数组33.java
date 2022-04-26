package william.interview.algo.binarysearch;

/**
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/description/
 */
public class 搜索旋转排序数组33 {
    public int search(int[] nums, int target) {
        //边界条件
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int l = 0;
        int r = nums.length - 1;

        //二分查找
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            //首先判断出左、右哪个部分是有序的
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] >= nums[0]) {  //左半边有序
                if (nums[0] <= target && nums[mid] > target) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {    //右半边有序
                if (nums[mid] < target && nums[nums.length - 1] >= target) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }

            }
        }

        //未找到,返回-1
        return -1;
    }

}
