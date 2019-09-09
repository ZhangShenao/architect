package william.algo.sort;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/2 17:51
 * @Description:插入排序
 */
public class InsertionSorter implements Sorter {
    @Override
    public void sort(int[] arr) {
        //[0,i]范围内已经有序
        for (int i = 1, len = arr.length; i < len; i++) {
            //记录i处的元素
            int ele = arr[i];
            //从i-1位置处开始,找到ele合适的位置进行插入
            int j = i - 1;
            while (j >= 0 && ele < arr[j]) {
                arr[j + 1] = arr[j];
                --j;
            }

            //插入的位置为j+1
            arr[j + 1] = ele;
        }
    }
}
