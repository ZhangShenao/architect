package william.algo.sort;

import java.util.Arrays;

import william.algo.utils.ArrayUtils;

/**
 * @Author zhangshenao
 * @Date 2021-04-18
 * @Description 冒泡排序
 * 时间复杂度： 最好=O(n) 最坏=O(n^2) 平均=O(n^2)
 * 是原地排序，空间复杂度=O(1)
 * 是稳定排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = ArrayUtils.genRandomArr(10, -100, 100);
        System.out.println("原始数组: " + Arrays.toString(arr));
        sort(arr);
        System.out.println("排序后数组: " + Arrays.toString(arr));
    }

    private static void sort(int[] arr) {
        //每趟冒泡操作，都将本趟最大的元素放在合适的位置上

        //优化：记录每趟排序是否有交换，一旦没有交换，则表示排序完成，可以直接退出
        boolean updated = false;

        for (int i = 1; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    ArrayUtils.swap(arr, j, j + 1);
                    updated = true;
                }
            }

            if (!updated) {
                return;
            }
        }
    }
}
