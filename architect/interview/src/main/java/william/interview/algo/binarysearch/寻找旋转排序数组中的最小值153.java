package william.interview.algo.binarysearch;

/**
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
 */
public class 寻找旋转排序数组中的最小值153 {
    public int findMin(int[] nums) {
        //边界条件
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int l = 0;
        int r = nums.length - 1;
        //二分查找
        while (l <= r) {
            int mid = l + ((r - l) >> 1);

            //mid在数组的左半部分
            if (nums[mid] >= nums[0]) {
                if (nums[mid] <= nums[r]) {  //整个数组有序,直接返回第一个元素
                    return nums[0];
                }
                l = mid + 1;
            } else {    //mid在数组的有半部分,说明数组发生了翻转
                if (mid > 0 && nums[mid - 1] < nums[mid]) { //判断左侧是否还有比nums[mid]小的元素
                    r = mid - 1;
                } else {
                    return nums[mid];
                }
            }
        }

        return nums[l];
    }
}
