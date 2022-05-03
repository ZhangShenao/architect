package william.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/3 上午10:33
 * <p>
 * https://leetcode-cn.com/problems/two-sum/
 */
public class 两数之和_1 {
    public int[] twoSum(int[] nums, int target) {
        //边界条件
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        //使用一个Map保存遍历过的元素及其下标,避免重复计算
        Map<Integer, Integer> cache = new HashMap<>(nums.length);    //key=num value=index

        for (int i = 0; i < nums.length; i++) {
            //计算target与当前元素的差值,如果在map中找到了差值,则直接返回结果
            int minus = target - nums[i];
            if (cache.containsKey(minus)) {
                int idx = cache.get(minus);
                return new int[]{idx, i};
            }

            //差值不存在,保存当前元素
            cache.put(nums[i], i);
        }

        //未找到,返回空数组
        return new int[0];
    }
}
