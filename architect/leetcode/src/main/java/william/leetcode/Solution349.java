package william.leetcode;

import java.util.*;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/14 13:34
 * @Description:https://leetcode.com/problems/intersection-of-two-arrays/
 */
public class Solution349 {
    public static void main(String[] args) {
        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9, 4, 9, 8, 4};

        System.err.println(Arrays.toString(intersection(nums1, nums2)));

    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> nums = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            if (!nums.contains(nums1[i])) {
                nums.add(nums1[i]);
            }
        }
        List<Integer> intersections = new LinkedList<>();
        for (int i = 0; i < nums2.length; i++) {
            if (nums.contains(nums2[i])) {
                intersections.add(nums2[i]);
                nums.remove(nums2[i]);
            }
        }
        if (intersections.size() == 0) {
            return new int[]{};
        }
        int[] result = new int[intersections.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = intersections.get(i);
        }
        return result;
    }

}
