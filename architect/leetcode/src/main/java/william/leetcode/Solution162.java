package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/19 11:16
 * @Description:https://leetcode.com/problems/find-peak-element/
 */
public class Solution162 {
    public static void main(String[] args) {
        Solution162 s = new Solution162();
        int[] nums = {1, 2, 1, 3, 5, 6, 4};
        System.err.println(s.findPeakElement(nums));
    }

    public int findPeakElement(int[] nums) {
        //处理空数组
        if (nums == null || nums.length == 0) {
            return -1;
        }

        //处理单个元素数组,直接返回这个元素
        if (nums.length == 1) {
            return 0;
        }

        //处理头、尾元素
        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[nums.length - 1] > nums[nums.length - 2]) {
            return nums.length - 1;
        }

        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return -1;
    }

}
