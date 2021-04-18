package william.algo.sort;

import java.util.Arrays;

import william.algo.utils.ArrayUtils;

/**
 * @Author zhangshenao
 * @Date 2021-04-18
 * @Description 插入排序
 * 将整个数组划分为有序和无序两个部分，将无序部分中的每一个元素插入到有序部分的对应位置，重复此过程直至无序部分为空，排序结束
 * 时间复杂度： 最好=O(n) 最坏=O(n^2) 平均=O(n^2)
 * 是原地排序，空间复杂度=O(1)
 * 是稳定排序
 * 插入排序是O(n^2)级别的排序中最优的
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = ArrayUtils.genRandomArr(10, -100, 100);
        System.out.println("原始数组: " + Arrays.toString(arr));
        sort(arr);
        System.out.println("排序后数组: " + Arrays.toString(arr));
    }

    private static void sort(int[] arr) {
        //初始状态:第一个元素有序，后面元素都无序
        for (int i = 1; i < arr.length; i++) {
            int idx = i;
            //从无序部分的第一个元素开始,在有序部分中找到合适的位置插入
            int tmp = arr[i];

            //找到元素在有序部分中应该插入的位置
            int j = i - 1;
            while (j >= 0 && tmp < arr[j]) {
                idx = j;
                j--;
            }

            //需要插入,元素整体移动
            if (idx != i) {
                for (int k = i; k > idx; k--) {
                    arr[k] = arr[k - 1];
                }
                arr[idx] = tmp;
            }

        }
    }
}
