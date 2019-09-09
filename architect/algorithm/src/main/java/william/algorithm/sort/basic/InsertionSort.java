package william.algorithm.sort.basic;

import william.algorithm.util.ArrayUtils;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/16 07:33
 * @Description:插入排序 从第二个元素开始, 选择合适的位置插入
 * 时间复杂度O(n*n) 空间复杂度O(1)
 * 稳定排序
 * 对于近乎有序的数组,插入排序效率很特别高,近乎达到O(n)
 */
public class InsertionSort {
    private static void insertionSort(int[] arr) {
        //认为第一个元素已经有序,从第二个元素开始遍历
        for (int i = 1; i < arr.length; i++) {
            //用tmp记录下arr[i]
            int tmp = arr[i];

            //j表示tmp应该插入到的位置
            int j = i;

            //从第j个元素开始,依次向前查找tmp应该插入到的位置
            //在元素已经有序的情况下,可以提前终止内存循环,这是插入排序一个十分重要的性质,使得插入排序在近乎有序的数组下效率非常高
            for (; j > 0 && tmp < arr[j - 1]; j--) {
                //移动元素
                arr[j] = arr[j - 1];
            }

            //将tmp放入指定位置
            arr[j] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] arr = ArrayUtils.generateRandomArray(10, -100, 100);
        ArrayUtils.printArray(arr);
        insertionSort(arr);
        ArrayUtils.printArray(arr);
    }
}
