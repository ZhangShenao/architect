package william.interview.algo.sort;

import william.interview.algo.utils.ArrayGenerator;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertionSort {
    public static void sort(int[] arr) {
        //边界条件
        if (arr == null || arr.length <= 0){
            return;
        }

        for (int i = 1; i < arr.length; i++) {
            //对[0,i)区间内的元素进行排序
            //i代表当前待插入的元素

            //记录当前元素
            int cur = arr[i];
            //向左寻找插入位置
            int j = i;

            while (j > 0 && arr[j - 1] > cur) {
                arr[j] = arr[j - 1];
                j--;
            }

            //j为需要插入的位置
            arr[j] = cur;
        }
    }

    public static void main(String[] args) {
        int[] arr = ArrayGenerator.genRandomIntegerArr(20, -1000, 1000);
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
