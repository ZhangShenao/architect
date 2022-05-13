package william.leetcode.sort;

import java.util.Arrays;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2022/5/13 上午11:23
 * 插入排序:将整个数组划分为已排序和未排序两个部分
 * 每次从未排序部分中取出第一个元素,插入到已排序部分中的指定位置
 * 待未排序部分为空时,整个数组就是有序的了
 * 时间复杂度O(N*N)
 * 空间复杂度O(1)
 */
public class 插入排序 {
    public static void sort(int[] arr) {
        //边界条件
        if (arr == null || arr.length == 0) {
            return;
        }

        //第一个元素默认为有序,无序部分从第二个元素开始
        for (int i = 1; i < arr.length; i++) {
            //取出无序部分的第一个元素,插入到有序部分中的指定位置
            int j = i;
            int cur = arr[j];   //记录当前元素

            //在有序部分中找到插入位置
            while (j > 0 && arr[j - 1] > cur) {
                //移动元素
                arr[j] = arr[j - 1];
                --j;
            }

            //将元素插入指定位置
            arr[j] = cur;
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
