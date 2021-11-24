package william.enhance.linearsearch;


import william.enhance.utils.ArrayGenerator;

import java.util.Arrays;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2021/11/24 上午9:47
 * <p>
 * 线性查找法
 * 时间复杂度O(n)
 */
public class LinearSearch {
    /**
     * 在泛型数组data中,线性查找元素target,返回target所在的下标。如果未找到则返回-1
     */
    public static <T> int search(T[] data, T target) {
        //边界
        if (data == null || data.length == 0) {
            return -1;
        }

        //线性查找
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(target)) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int size = 10000000;
        Integer[] arr = ArrayGenerator.genRandomIntegerArr(size, -1000, 1000);
        System.err.println("Arr: " + Arrays.toString(arr));
        int target = 1000;
        long startTime = System.nanoTime();
        int idx = search(arr, target);
        long endTime = System.nanoTime();
        System.err.println("Find Index: " + idx);
        System.err.println("Cost Time: " + (endTime - startTime) / 1000L + " ms");
    }
}
