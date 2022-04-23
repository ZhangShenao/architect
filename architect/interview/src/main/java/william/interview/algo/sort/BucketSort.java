package william.interview.algo.sort;

import william.interview.algo.utils.ArrayGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 桶排序
 */
public class BucketSort {
    /**
     * 桶排序
     *
     * @param arr        待排序数组
     * @param min        元素最小值
     * @param max        元素最大值
     * @param bucketSize 桶数量
     * @return 排好序的数组
     */
    public static List<Integer> sort(int[] arr, int min, int max, int bucketSize) {
        if (arr == null || arr.length <= 0) {    //边界条件
            return Collections.emptyList();
        }

        //创建桶
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < bucketSize; i++) {
            buckets.add(i, new ArrayList<>());
        }

        //遍历数组,存放元素
        for (int i = 0; i < arr.length; i++) {
            int bucketIndex = bucketIndex(arr[i], min, max, bucketSize);
            buckets.get(bucketIndex).add(arr[i]);
        }

        List<Integer> result = new ArrayList<>();

        //遍历桶,获取排好序的元素
        for (List<Integer> bucket : buckets) {
            Integer[] array = bucket.toArray(new Integer[0]);
            QuickSort.sort(array);
            result.addAll(Arrays.stream(array).collect(Collectors.toList()));
        }

        return result;
    }

    //计算桶索引
    private static int bucketIndex(int value, int min, int max, int bucketSize) {
        int range = max - min + 1;
        return (value - min) * bucketSize /range;
    }

    public static void main(String[] args) {
        int[] arr = ArrayGenerator.genRandomIntegerArr(1000, 0, 99);
        List<Integer> result = sort(arr, 0, 99, 10);
        System.out.println(result);
    }
}
