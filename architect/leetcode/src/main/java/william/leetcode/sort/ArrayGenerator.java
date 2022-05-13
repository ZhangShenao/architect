package william.leetcode.sort;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @Description
 * @Author ZhangShenao
 * @Date 2021/11/24 上午9:52
 * <p>
 * 数组生成器
 */
public class ArrayGenerator {
    /**
     * 生成随机整型数组
     */
    public static Integer[] genRandomIntegerArr(int size, int min, int max) {
        Integer[] arr = new Integer[size];
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(min, max + 1);
        }
        return arr;
    }

    /**
     * 生成随机整型数组
     */
    public static int[] genRandomIntArr(int size, int min, int max) {
        int[] arr = new int[size];
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(min, max + 1);
        }
        return arr;
    }
}
