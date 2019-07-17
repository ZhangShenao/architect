package william.utils;

import william.enhance.sort.Sorter;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/17 17:40
 * @Description:
 */
public class AlgorithmUtils {
    /**
     * 生成随机的整形数组
     *
     * @param size 数组长度
     * @param min  最小值
     * @param max  最大值
     * @return 生成的数组
     */
    public static Integer[] genRandomArray(int size, int min, int max) {
        Integer[] arr = new Integer[size];
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(min, max + 1);
        }
        return arr;
    }

    /**
     * 交换数组中的两个元素
     */
    public static <T> void swap(T[] arr, int i, int j) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 打印数组
     */
    public static <T> void print(String title, T[] arr) {
        System.err.println(title + ": " + Arrays.toString(arr));
    }

    /**
     * 排序性能测试
     */
    public static <T extends Comparable<T>> void sortBenchmark(Class<? extends Sorter> sorterClass, T[] arr) {
        try {
            print("排序前", arr);
            Sorter sorter = sorterClass.newInstance();
            long startTime = System.nanoTime();
            sorter.sort(arr);
            print("排序后", arr);
            System.err.println("是否升序: " + isSortedAsc(arr));
            System.err.println("耗时: " + (System.nanoTime() - startTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断一个数组是否有序
     */
    public static <T extends Comparable<T>> boolean isSortedAsc(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareTo(arr[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }
}
