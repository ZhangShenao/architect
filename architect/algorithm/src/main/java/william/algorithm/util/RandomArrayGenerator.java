package william.algorithm.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/7 14:36
 * @Description:随机数组生成器
 */
public class RandomArrayGenerator {
    public static int[] generateRandomArray(int size,int minValue,int maxValue){
        int[] arr = new int[size];
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0;i < size;i++){
            arr[i] = random.nextInt(minValue,maxValue + 1);
        }
        return arr;
    }
}
