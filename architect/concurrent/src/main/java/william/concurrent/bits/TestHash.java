package william.concurrent.bits;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author zhangshenao
 * @Date 2019-09-03
 * @Description 有趣的取模性质：取模a % (2^n) 等价于 a & (2^n - 1)，所以在map里的数组个数一定是2的乘方数，计算key值在哪个元素中的时候，就用位运算来快速定位。
 */
public class TestHash {
    public static void main(String[] args) {
        //创建一个2的指数n
        int n = (int) Math.pow(2, ThreadLocalRandom.current().nextInt(1, 10));

        //创建一个随机正整数h
        int h = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
        System.err.println("取模运算与位运算的结果是否相等: " + ((h % n) == (h & (n - 1))));
    }
}
