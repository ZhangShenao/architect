package william.algorithm.sort;

import william.algorithm.util.RandomArrayGenerator;

import java.util.Arrays;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/7 16:49
 * @Description:插入排序，每趟的比较中可以提前结束，在数据大部分有序时，效率非常高
 */
public class InsertionSort {
    public static void insertionSort(int[] arr){
        for (int i = 1,len = arr.length;i < len;i++){
            int tmp = arr[i];

            int j = i;
            for (;j > 0 && tmp < arr[j - 1];j--){
                arr[j] = arr[j - 1];
            }
            arr[j] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] array = RandomArrayGenerator.generateRandomArray(20, -100, 100);
        System.err.println(Arrays.toString(array));
        insertionSort(array);
        System.err.println(Arrays.toString(array));
    }
}
