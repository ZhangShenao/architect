package william.leetcode.binarysearch;

/**
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
 */
public class 寻找旋转排序数组中的最小值153 {
    public int findMin(int[] nums) {
        //边界条件
        if (nums == null || nums.length == 0) {
            return -1;
        }

        //采用二分查找
        int n = nums.length;
        int l = 0;
        int r = n - 1;

        while (l < r) { //因为数组是不重复的,所以l和r不会重叠
            int mid = l + ((r - l) >> 1);

            //将mid与r处的元素进行比较,判断最小值在mid的左侧还是右侧
            if (nums[mid] <= nums[r]) {  //最小值的mid的左侧,丢弃右半部分
                r = mid;
            } else {    //最小值在mid的右侧,丢弃左半部分
                l = mid + 1;
            }
        }

        //直接返回l处的元素
        return nums[l];
    }
}
