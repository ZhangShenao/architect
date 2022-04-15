package william.interview.algo.utils;

import java.util.concurrent.ThreadLocalRandom;

public class ArrayGenerator {
    /**
     * 生成随机整型数组
     */
    public static int[] genRandomIntegerArr(int size, int min, int max) {
        int[] arr = new int[size];
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(min, max + 1);
        }
        return arr;
    }
}
