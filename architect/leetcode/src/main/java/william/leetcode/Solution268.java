package william.leetcode;

import java.util.Arrays;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/17 12:00
 * @Description:https://leetcode.com/problems/missing-number/
 */
public class Solution268 {
    public int missingNumber(int[] nums) {
        //计算数组实际的和
        int realSum = Arrays.stream(nums).sum();

        //计算期望的和
        int expectedSum = calculateExpectedSum(nums.length);

        //期望和与实际和的差值即丢失的元素
        return expectedSum - realSum;
    }

    private int calculateExpectedSum(int length) {
        return length * (length + 1) / 2;
    }
}
