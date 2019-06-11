package william.leetcode;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/10 20:30
 * @Description:https://leetcode.com/problems/third-maximum-number/
 */
public class Solution414 {
    public static void main(String[] args) {
        int[] arr = {1, 2};
        System.err.println(thirdMax(arr));
    }

    public static int thirdMax(int[] nums) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(nums.length, Collections.reverseOrder());
        for (int num : nums) {
            if (!queue.contains(num)) {
                queue.offer(num);
            }
        }
        int count = queue.size() >= 3 ? 3 : 1;
        int third = 0;
        for (int i = 0; i < count; i++) {
            third = queue.poll();
        }
        return third;

    }
}
