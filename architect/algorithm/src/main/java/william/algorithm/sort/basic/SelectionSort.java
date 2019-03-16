package william.algorithm.sort.basic;

import william.algorithm.util.ArrayUtils;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/15 10:31
 * @Description:选择排序
 * 每次选择剩余元素中最小的,放在首位
 * 时间复杂度O(n*n) 空间复杂度O(1)
 *
 */
public class SelectionSort {
    public static void selectionSort(int[] arr){
        for (int i = 0;i < arr.length;i++){
            int minIndex = i;
            for (int j = i;j < arr.length;j++){
                if (arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            if (minIndex != i){
                ArrayUtils.swapArrayElement(arr,minIndex,i);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = ArrayUtils.generateRandomArray(10, -100, 100);
        ArrayUtils.printArray(arr);
        selectionSort(arr);
        ArrayUtils.printArray(arr);
    }
}
