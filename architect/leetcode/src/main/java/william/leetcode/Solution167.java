package william.leetcode;

import java.util.Arrays;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/3 17:19
 * @Description:https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 */
public class Solution167 {
    public static void main(String[] args) {
        int[] numbers = {2, 7, 11, 15};
        int target = 9;
        System.err.println(Arrays.toString(twoSum(numbers, target)));
    }

    public static int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            }
            if (sum > target) {
                --right;
            } else {
                ++left;
            }
        }
        return null;
    }
}
