package william.leetcode.binarysearch;

/**
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/description/
 */
public class 搜索旋转排序数组_33 {
    public int search(int[] nums, int target) {
        //边界条件
        if (nums == null || nums.length == 0) {
            return -1;
        }

        //采用二分搜索
        int n = nums.length;
        int l = 0;
        int r = n - 1;

        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] == target) {
                return mid;
            }
            //因为有序数组经过一次翻转,所以mid的左、右两部分中一定有一边是有序的,所以先判断是哪边有序,然后丢弃无序的一边

            if (nums[mid] >= nums[0]) {  //左边有序
                if (nums[mid] > target && nums[0] <= target) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else { //右边有序
                if (nums[mid] < target && nums[n - 1] >= target) {
                    l = mid + 1;
                } else r = mid - 1;
            }

        }

        //未找到
        return -1;
    }

    public static void main(String[] args) {
        搜索旋转排序数组_33 s = new 搜索旋转排序数组_33();
        int[] nums = new int[]{4, 5, 6, 7, 8, 1, 2, 3};
        int target = 8;
        System.out.println(s.search(nums, target));
    }

}
