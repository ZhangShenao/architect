package william.interview.algo.utils;

public class Swap {
    //交换数组i,j下标处的元素
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    //交换数组i,j下标处的元素
    public static void swap(Integer[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
