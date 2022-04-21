package william.interview.algo.heap;

import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
 */
public class 最小的k个数 {
    public int[] getLeastNumbers(int[] arr, int k) {
        //边界条件
        if (arr == null || arr.length <= 0 || k <= 0 || k > arr.length) {
            return new int[0];
        }

        //使用大顶堆
        PriorityQueue<Integer> heap = new PriorityQueue<>((x, y) -> y - x);

        for (int i = 0; i < arr.length; i++) {
            if (heap.size() < k) {   //堆未满,当前元素直接放入堆中
                heap.offer(arr[i]);
                continue;
            }

            //如果当前元素大于等于堆顶元素,则直接跳过
            if (arr[i] >= heap.peek()) {
                continue;
            }

            //将当前元素放入堆中
            heap.offer(arr[i]);

            //移除堆顶元素(最大元素)
            heap.poll();
        }

        //返回数组
        int[] result = new int[heap.size()];
        int i = 0;
        while (!heap.isEmpty()) {
            result[i++] = heap.poll();
        }

        return result;
    }
}
