package william.algo.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author zhangshenao
 * @Date 2021-04-18
 * @Description 数组相关的工具类
 */
public class ArrayUtils {
    /**
     * 交换数组中的两个元素
     *
     * @param arr 原始数组
     * @param i 位置1
     * @param j 位置2
     */
    public static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 生成随机的int型数组
     *
     * @param size 数组长度
     * @param min 元素最小值
     * @param max 元素最大值
     * @return 生成的数组
     */
    public static int[] genRandomArr(int size, int min, int max) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(min, max + 1);
        }
        return arr;
    }
}
