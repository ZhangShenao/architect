package william.algo.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhangshenao
 * @Date 2021-11-02
 * @Description https://leetcode-cn.com/problems/two-sum/
 * <p>
 * 方法一：穷举，遍历两次数组，时间复杂度O(n*n)
 * 方法二：借助Map，时间复杂度O(n)，空间复杂度O(n)
 * <p>
 * 借助Set，常常可以节省O(n)的时间复杂度
 */
public class 两数之和_1 {
    public int[] twoSum(int[] nums, int target) {
        //边界
        if (nums == null || nums.length == 0) {
            return nums;
        }

        //遍历数组,判断当前元素与之前元素的和是否等于target
        Map<Integer, Integer> saved = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            int left = target - cur;
            if (saved.containsKey(left)) {  //保存遍历到的元素和其对应的下标
                int idx = saved.get(left);
                return new int[] {idx, i};
            } else {
                saved.put(cur, i);
            }
        }

        //没有匹配的元素,返回空数组
        return new int[] {};
    }

}
