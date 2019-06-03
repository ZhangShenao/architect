package william.algo.sort;

import william.algorithm.util.ArrayUtils;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/2 17:44
 * @Description:冒泡排序
 */
public class BubbleSorter implements Sorter {
    @Override
    public void sort(int[] arr) {
        //[len - i,len]范围内的元素已经有序
        for (int i = 0, len = arr.length; i < len; i++) {
            boolean swap = false;
            for (int j = 0; j < len - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    ArrayUtils.swapArrayElement(arr, j, j + 1);
                    swap = true;
                }
            }
            //优化:如果某趟比较没有元素交换,说明数组已经有序,则直接结束排序
            if (!swap) {
                return;
            }
        }
    }
}
