package william.algo.sort;

import william.algorithm.util.ArrayUtils;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/2 18:02
 * @Description:选择排序
 */
public class SelectionSorter implements Sorter {
    @Override
    public void sort(int[] arr) {
        //[0,i]已经有序
        for (int i = 0, len = arr.length; i < len; i++) {
            int minIdx = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[minIdx]){
                    minIdx = j;
                }
            }
            if (minIdx != i){
                ArrayUtils.swapArrayElement(arr,minIdx,i);
            }
        }
    }
}
