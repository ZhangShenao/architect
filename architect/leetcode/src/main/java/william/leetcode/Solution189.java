package william.leetcode;

import java.util.Arrays;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/3 17:30
 * @Description:https://leetcode.com/problems/rotate-array/
 */
public class Solution189 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        rotate(nums, k);
        System.err.println(Arrays.toString(nums));
    }

    public static void rotate(int[] nums, int k) {
        int len = nums.length;
        //避免k>数组长度造成越界
        k = k % len;
        //保存倒数后k个元素
        int[] tmp = new int[k];
        for (int i = 0; i < k; i++) {
            tmp[i] = nums[len - (k - i)];
        }

        //将原数组的[0,len - 1 - k]范围内的元素向后移动k位
        for (int i = len - 1 - k; i >= 0; i--) {
            nums[i + k] = nums[i];
        }

        //将保存好的元素拷贝到新数组
        for (int i = 0; i < k; i++) {
            nums[i] = tmp[i];
        }
    }
}
