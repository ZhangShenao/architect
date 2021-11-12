package william.algo.basic.sort;

import java.util.Arrays;

/**
 * @Author zhangshenao
 * @Date 2021-11-11
 * @Description 选择排序
 */
public class SelectionSort {
    public void sort(int[] arr) {
        //边界
        if (arr == null || arr.length == 0) {
            return;
        }

        //遍历n次数组,每次从剩余的区间中取出最小的元素,放在合适的位置
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            //交换
            swap(arr, i, minIdx);

        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        SelectionSort sort = new SelectionSort();
        int[] arr = new int[] {-100, 99, -200, 98, -300, 97, 0, 0, 100, 200, 100, 200, -999};
        sort.sort(arr);
        System.err.println(Arrays.toString(arr));
    }
}
