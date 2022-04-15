package william.interview.algo.sort;

import william.interview.algo.utils.ArrayGenerator;
import william.interview.algo.utils.Swap;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void sort(int[] arr) {
        //边界条件
        if (arr == null || arr.length <= 0) {
            return;
        }

        //在[0,i]范围内找到最大的元素,放到i下标处
        //i为无序|有序的边界
        for (int i = arr.length - 1; i > 0; i--) {
            //找到[0,i]范围内进行bubble操作
           boolean swap = bubble(arr,0,i);

           //优化:如果当前一趟没有元素交换,说明数组已经有序,可以提前退出
            if (!swap){
                return;
            }
        }
    }

    //Bubble:在arr[start,end]区间内,如果发现后面元素比前面元素小,则进行交换
    private static boolean bubble(int[] arr, int start, int end)  {
        boolean swap = false;
        for (int i = start; i <= end - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                swap = true;
                Swap.swap(arr, i, i + 1);
            }
        }
        return swap;
    }

    public static void main(String[] args) {
        int[] arr = ArrayGenerator.genRandomIntegerArr(20, -1000, 1000);
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
