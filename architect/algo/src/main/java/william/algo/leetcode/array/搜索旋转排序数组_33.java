package william.algo.leetcode.array;

/**
 * @Author zhangshenao
 * @Date 2021-11-13
 * @Description https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 * <p>
 * 二分查找的变种:在分段有序的数组中进行二分查找
 * <p>
 * 时间复杂度 O(logn)
 */
public class 搜索旋转排序数组_33 {
    public int search(int[] nums, int target) {
        //边界
        if (nums == null || nums.length == 0) {
            return -1;
        }

        //二分查找
        int min = 0;
        int max = nums.length - 1;
        while (min <= max) {
            int mid = min + (max - min) / 2;
            if (nums[mid] == target) {  //找到直接返回
                return mid;
            }

            //需要先判断mid的左、右哪部分是有序的,再判断target在哪个区间范围内
            if (nums[min] <= nums[mid]) { //左侧有序
                if (nums[mid] > target && nums[min] <= target) {    //往左找
                    max = mid - 1;
                } else { //往右找
                    min = mid + 1;
                }
            } else { //右侧有序
                if (nums[mid] < target && nums[max] >= target) {    //往右找
                    min = mid + 1;
                } else {
                    max = mid - 1;
                }
            }
        }

        //未找到
        return -1;
    }

    public static void main(String[] args) {
        搜索旋转排序数组_33 s = new 搜索旋转排序数组_33();
        int[] nums = new int[] {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        System.err.println(s.search(nums, target));
    }
}
