package william.algorithm.sort.basic;

import william.algorithm.util.ArrayUtils;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/16 08:18
 * @Description:冒泡排序 每次比较相邻的两个元素, 让比较大的元素排在后面
 * 时间复杂度O(n*n) 空间复杂度O(1)
 */
public class BubbleSort {
    private static void bubbleSort(int[] arr) {
        for (int j = arr.length - 1; j > 0; j--) {
            //每趟从0开始比较到j

            //优化:如果某趟没有交换,说明数组已经有序,可以退出排序
            boolean swap = false;
            for (int i = 0; i < j; i++) {
                if (arr[i] > arr[i + 1]) {
                    ArrayUtils.swapArrayElement(arr, i, i + 1);
                    swap = true;
                }
            }
            if (!swap) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = ArrayUtils.generateRandomArray(10, -100, 100);
        ArrayUtils.printArray(arr);
        bubbleSort(arr);
        ArrayUtils.printArray(arr);
    }
}
