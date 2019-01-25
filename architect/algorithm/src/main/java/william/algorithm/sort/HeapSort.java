package william.algorithm.sort;

import william.algorithm.heap.MaxHeap;
import william.algorithm.util.RandomArrayGenerator;
import java.util.Arrays;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/13 11:34
 * @Description:堆排序，时间复杂度:O(nlogn) 空间复杂度:O(n)
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] array = RandomArrayGenerator.generateRandomArray(10, -100, 100);
        System.err.println(Arrays.toString(array));
        heapSort(array);
        System.err.println(Arrays.toString(array));
    }

    public static void heapSort(int[] arr){
        int len = arr.length;
        MaxHeap maxHeap = new MaxHeap(arr);
        for (int i = len - 1;i >= 0;i--){
            if (!maxHeap.isEmpty()){
                arr[i] = maxHeap.getLargest();
            }
        }
    }
}
