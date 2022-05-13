package william.leetcode.sort;

import java.util.Arrays;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/13 上午9:17
 * 选择排序:对数组进行N次遍历,每次选择剩余数组中最小的,放在指定位置
 * 时间复杂度O(N*N)
 * 空间复杂度O(1)
 */
public class 选择排序 {
    public static void sort(int[] arr) {
        //边界条件
        if (arr == null || arr.length == 0) {
            return;
        }

        //对数组进行N次遍历
        for (int i = 0; i < arr.length; i++) {
            //从i位置开始,选择最小的元素,和i处的元素进行交换
            int mixIdx = i;
            for (int j = i; j < arr.length; j++) {
                //选出最小元素
                if (arr[j] < arr[mixIdx]) {
                    mixIdx = j;
                }
            }

            //将最小元素与i处的元素进行交换
            int tmp = arr[i];
            arr[i] = arr[mixIdx];
            arr[mixIdx] = tmp;
        }

    }

    public static void main(String[] args) {
        int[] arr = ArrayGenerator.genRandomIntArr(10, -1000, 1000);
        System.out.println("原始数组");
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println("排序后数组");
        System.out.println(Arrays.toString(arr));
    }
}
