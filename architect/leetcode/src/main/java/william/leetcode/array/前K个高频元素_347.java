package william.leetcode.array;

import java.util.*;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/23 上午10:43
 * <p>
 * https://leetcode.cn/problems/top-k-frequent-elements/
 */
public class 前K个高频元素_347 {
    //首先遍历一遍数组,使用map保存每个元素及其出现的次数
    //然后基于堆排序,创建小顶堆,保存出现频率最高的k个元素
    //最后从堆中返回结果
    //时间复杂度O(N*logN)
    //空间复杂度O(N)
    public int[] topKFrequent(int[] nums, int k) {
        //边界条件
        if (nums == null || nums.length == 0 || k < 1 || k > nums.length) {
            return new int[0];
        }

        //遍历数组,使用map保存每个元素及其出现的次数
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        //创建最小堆,保存出现频率最高的k个元素
        //堆中元素是一个只有两个元素的数组,第一个元素是数值,第二个元素是数值出现的次数
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(k, Comparator.comparingInt(o -> o[1]));

        //遍历map,按照出现次数构建堆
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            Integer num = e.getKey();
            Integer times = e.getValue();
            if (minHeap.size() < k) {   //堆未满,直接将元素入堆
                minHeap.offer(new int[]{num, times});
                continue;
            }

            //堆已满,如果当前元素出现次数大于堆顶元素,则使用当前元素替换堆顶元素
            if (times > minHeap.peek()[1]) {
                minHeap.poll();
                minHeap.offer(new int[]{num, times});
            }
        }

        //遍历堆,返回结果
        int[] result = new int[k];
        int i = k - 1;
        while (!minHeap.isEmpty()) {
            int n = minHeap.poll()[0];
            result[i--] = n;
        }

        return result;
    }

    public static void main(String[] args) {
        前K个高频元素_347 s = new 前K个高频元素_347();
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] result = s.topKFrequent(nums, k);
        System.out.println(Arrays.toString(result));
    }
}
