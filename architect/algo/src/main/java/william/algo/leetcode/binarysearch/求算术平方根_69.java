package william.algo.leetcode.binarysearch;

/**
 * @Author zhangshenao
 * @Date 2021-11-07
 * @Description https://leetcode-cn.com/problems/sqrtx/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign
 * =transfer2china
 * 基于二分查找的思想 时间复杂度O(logn)
 */
public class 求算术平方根_69 {
    public int mySqrt(int x) {
        //边界
        if (x < 0) {
            throw new IllegalArgumentException();
        }

        if (x == 0) {
            return x;
        }

        int min = 1;
        int max = x;
        int sqrt = min;   //从1开始

        //通过min和max进行夹逼
        while (sqrt <= max && sqrt >= min) {
            if (min == max - 1) {
                return min;
            }
            int res = x / sqrt;
            if (res == sqrt) {
                return sqrt;
            }
            if (res > sqrt) {
                min = sqrt++;
            } else {
                max = sqrt--;
            }
        }

        return x;
    }

    public static void main(String[] args) {
        求算术平方根_69 res = new 求算术平方根_69();
        System.err.println(res.mySqrt(9));
    }
}
