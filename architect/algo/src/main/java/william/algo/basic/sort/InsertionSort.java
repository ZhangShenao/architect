package william.algo.basic.sort;

import java.util.Arrays;

/**
 * @Author zhangshenao
 * @Date 2021-11-11
 * @Description 插入排序
 */
public class InsertionSort {
    public void sort(int[] arr) {
        //边界
        if (arr == null || arr.length == 0) {
            return;
        }

        //将整个数组分为已排序和未排序两个部分,遍历数组未排序部分的每个元素,插入到已排序部分合适的位置中
        for (int i = 1; i < arr.length; i++) {
            int cur = arr[i];

            //找到合适的位置插入
            int j = i - 1;
            while (j >= 0 && cur < arr[j]) {
                arr[j + 1] = arr[j];
                --j;
            }
            arr[j + 1] = cur;
        }
    }

    public static void main(String[] args) {
        InsertionSort sort = new InsertionSort();
        int[] arr = new int[] {-100, 99, -200, 98, -300, 97, 0, 0, 100, 200, 100, 200, -999};
        sort.sort(arr);
        System.err.println(Arrays.toString(arr));
    }
}
