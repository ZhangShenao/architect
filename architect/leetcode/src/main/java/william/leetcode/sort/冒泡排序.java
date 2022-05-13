package william.leetcode.sort;

import java.util.Arrays;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/13 上午9:06
 * <p>
 * 冒泡排序:对数组进行N趟遍历,每趟一次两两比较相邻的元素,如果前面元素比后面元素大,则交换两个元素。
 * 每趟都会使得剩余元素中最大的移动到数组末尾
 * 时间复杂度O(N*N)
 * 空间复杂度O(1)
 */
public class 冒泡排序 {
    public static void sort(int[] arr) {
        //边界
        if (arr == null || arr.length == 0) {
            return;
        }

        //对数组进行N趟遍历
        for (int i = 0; i < arr.length; i++) {
            //每趟从头开始,两两比较剩余元素,将最大元素交换到数组末尾
            boolean swap = false;   //记录当前趟有没有元素交换
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {   //将更大的元素交换到后面
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    swap = true;
                }
            }

            //优化:如果当前趟没有发生元素交换,则说明数组已经有序,可以提前退出
            if (!swap) {
                return;
            }
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
