package william.algorithm.util;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/7 14:42
 * @Description:
 */
public class ArrayUtils {
    public static <T> void swapArrayElement(int[] arr,int i,int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static <T> void swapArrayElement(T[] arr,int i,int j){
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
