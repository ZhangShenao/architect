package william.leetcode;

import java.util.*;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/14 13:47
 * @Description:https://leetcode.com/problems/intersection-of-two-arrays-ii/
 */
public class Solution350 {
    public static void main(String[] args) {
        int[] nums1 = {1,2,2,1};
        int[] nums2 = {2,2};

        System.err.println(Arrays.toString(intersect(nums1, nums2)));

    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        //统计nums1中每个元素出现的次数
        Map<Integer,Integer> nums = new HashMap<>();
        for (int num : nums1){
            if (!nums.containsKey(num)){
                nums.put(num,1);
            }else {
                nums.put(num,nums.get(num) + 1);
            }
        }

        List<Integer> intersections = new LinkedList<>();
        //遍历nums2
        for (int num : nums2){
            if (!nums.containsKey(num)){
                continue;
            }
            int times = nums.get(num);
            if (times > 0){
                intersections.add(num);
                nums.put(num,--times);
            }
        }
        int[] result = new int[intersections.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = intersections.get(i);
        }
        return result;
    }
}
