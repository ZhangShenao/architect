package william.algo.basic.sort;

import java.util.Arrays;

/**
 * @Author zhangshenao
 * @Date 2021-11-11
 * @Description 冒泡排序
 */
public class BubbleSort {
    public void sort(int[] arr) {
        //边界
        if (arr == null || arr.length == 0) {
            return;
        }

        //遍历N趟数组,每趟通过交换,把最大的元素交换到数组末尾
        for (int i = 0; i < arr.length; i++) {
            boolean swap = false;
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap = true;
                    swap(arr, j, j + 1);
                }
            }
            if (!swap) {    //冒泡排序优化：通过一个标记位记录当前趟是否发生了元素交换。如果没有交换，说明当前数组已经有序了,排序完成
                return;
            }
        }


    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        BubbleSort sort = new BubbleSort();
        int[] arr = new int[] {-100, 99, -200, 98, -300, 97, 0, 0, 100, 200, 100, 200, -999};
        sort.sort(arr);
        System.err.println(Arrays.toString(arr));
    }
}
