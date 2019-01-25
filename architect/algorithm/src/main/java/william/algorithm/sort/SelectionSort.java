package william.algorithm.sort;

import william.algorithm.util.ArrayUtils;
import william.algorithm.util.RandomArrayGenerator;
import java.util.Arrays;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/7 14:35
 * @Description:选择排序,每趟都要进行元素的比较，效率较低
 */
public class SelectionSort {
    private static void selectionSort(int[] arr){
        for(int i = 0,len = arr.length;i < len - 1;i++){
            int minIndex = i;

            for (int j = i + 1;j < len;j++){
                if (arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }

            if (minIndex != i){
                ArrayUtils.swapArrayElement(arr,i,minIndex);
            }
        }
    }

    public static void main(String[] args) {
        int[] array = RandomArrayGenerator.generateRandomArray(20, -100, 100);
        System.err.println(Arrays.toString(array));
        selectionSort(array);
        System.err.println(Arrays.toString(array));
    }
}
