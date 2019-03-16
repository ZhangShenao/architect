package william.algorithm.util;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/7 14:42
 * @Description:
 */
public class ArrayUtils {
    public static void swapArrayElement(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static int[] generateRandomArray(int size,int minValue,int maxValue){
        int[] arr = new int[size];
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0;i < size;i++){
            arr[i] = random.nextInt(minValue,maxValue + 1);
        }
        return arr;
    }

    public static void printArray(int[] arr){
        System.err.println(Arrays.toString(arr));
    }
}
