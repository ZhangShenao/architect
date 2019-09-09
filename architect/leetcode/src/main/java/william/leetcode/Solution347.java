package william.leetcode;

import java.util.*;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/22 07:49
 * @Description:https://leetcode.com/problems/top-k-frequent-elements/
 */
public class Solution347 {
    public List<Integer> topKFrequent(int[] nums, int k) {
        //使用一个Map统计每个元素出现的次数
        Map<Integer, Integer> times = new HashMap<>();

        for (int num : nums) {
            if (!times.containsKey(num)) {
                times.put(num, 1);
            } else {
                times.put(num, times.get(num) + 1);
            }
        }

        //使用一个大顶堆,保存每个元素和其出现的次数
        PriorityQueue<Item> heap = new PriorityQueue<>(Comparator.reverseOrder());

        for (Map.Entry<Integer, Integer> entry : times.entrySet()) {
            heap.offer(new Item(entry.getKey(), entry.getValue()));
        }

        //获取前k个最频繁的元素
        List<Integer> kth = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            kth.add(heap.poll().num);
        }
        return kth;
    }

    private class Item implements Comparable<Item> {
        int num;
        int times;

        public Item(int num, int times) {
            this.num = num;
            this.times = times;
        }

        @Override
        public int compareTo(Item o) {
            return times - o.times;
        }
    }
}
