package william.algo.leetcode.hash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author zhangshenao
 * @Date 2021-11-02
 * @Description https://leetcode-cn.com/problems/3sum/
 * 遍历两层数组,借助Map保存之前两个元素的和，并与当前元素匹配
 * 时间复杂度O(n*n)
 */
public class 三数之和_15 {
    public List<List<Integer>> threeSum(int[] nums) {
        //边界
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }

        //使用Map保存两个元素的相加结果 key=两个元素的和 value=两个元素的索引
        Map<Integer, List<Integer>> map = new HashMap<>();


        //遍历两层数组,保存每两个元素的加和
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 1; i++) {
            int minus = -nums[i];
            if (map.containsKey(minus)) {   //进行元素匹配,保存结果
                List<Integer> elements = new ArrayList<>(map.get(minus));
                elements.add(nums[i]);
                result.add(elements);
            }
            for (int j = i + 1; j < nums.length; j++) { //保存两个元素的加和
                int sum = nums[i] + nums[j];
                List<Integer> elements = new ArrayList<>();
                elements.add(nums[i]);
                elements.add(nums[j]);
                map.put(sum, elements);
            }
        }


        return result;
    }

}
