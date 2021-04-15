package william.algo.sort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author zhangshenao
 * @Date 2021-04-15
 * @Description 排序算法
 */
public class SortAlgo {
    public static void main(String[] args) {
        int[] arr = genRandomArr(10, -100, 100);
        System.out.println("原始数组: " + Arrays.toString(arr));
        selectionSort(arr);
        System.out.println("排序后数组: " + Arrays.toString(arr));
    }

    /**
     * 冒泡排序
     * 时间复杂度： 最好=O(n) 最坏=O(n^2) 平均=O(n^2)
     * 是原地排序，空间复杂度=O(1)
     * 是稳定排序
     *
     * @param arr 待排序数组
     */
    private static void bubbleSort(int[] arr) {
        //每趟冒泡操作，都将本趟最大的元素放在合适的位置上

        //优化：记录每趟排序是否有交换，一旦没有交换，则表示排序完成，可以直接退出
        boolean updated = false;

        for (int i = 1; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    updated = true;
                }
            }

            if (!updated) {
                return;
            }
        }
    }

    /**
     * 插入排序
     * 将整个数组划分为有序和无序两个部分，将无序部分中的每一个元素插入到有序部分的对应位置，重复此过程直至无序部分为空，排序结束
     * 时间复杂度： 最好=O(n) 最坏=O(n^2) 平均=O(n^2)
     * 是原地排序，空间复杂度=O(1)
     * 是稳定排序
     *
     * @param arr 待排序数组
     */
    private static void insertionSort(int[] arr) {
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

    /**
     * 选择排序
     * 时间复杂度： 最好=O(n^2) 最坏=O(n^2) 平均=O(n^2)
     * 是原地排序，空间复杂度=O(1)
     * 不是稳定排序
     */
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            swap(arr, i, minIdx);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static int[] genRandomArr(int size, int min, int max) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(min, max + 1);
        }
        return arr;
    }
}
